package repositories

import db.tables.SpacesUsers
import db.tables.Users
import kotlinx.datetime.Clock
import kotlinx.datetime.toLocalDateTime
import mappers.toDomainUser
import model.entities.User
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class UserRepositoryImpl: UserRepository {
    override suspend fun insert(
        userName: String,
        email: String,
        password: String
    ): Result<Long> = transaction{
        try {
            val userId = Users.insert {
                it[Users.userName] = userName
                it[Users.email] = email
                it[Users.passwordHash] = password
                it[Users.routeImage] = null
                it[Users.createdAt] = Clock.System.now().toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault())
            } get Users.id
            Result.success(userId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun update(
        id: Long,
        newName: String?,
        newPassword: String?,
        newRouteImage: String?
    ): Result<Unit> = transaction {
        try {
            Users.update({Users.id eq id}) {
                if (newName != null) it[Users.userName] = newName
                if (newPassword != null) it[Users.passwordHash] = newPassword
                if (newRouteImage != null) it[Users.routeImage] = newRouteImage
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun delete(id: Long): Result<Unit> = transaction {
        try {
            Users.deleteWhere { Users.id eq id }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun findById(userId: Long): Result<User> = transaction {
        try {
            val user = Users.selectAll().where { Users.id eq userId }
                .map { it.toDomainUser() }
                .single()
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun findByEmail(email: String): Result<User> = transaction {
        try {
            val user = Users.selectAll().where { Users.email eq email }
                .map { it.toDomainUser() }
                .single()
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun findBySpaceId(spaceId: Long): Result<List<User>> = transaction {
        try {
            val listUserID = SpacesUsers.select(SpacesUsers.userId).where { SpacesUsers.spaceId eq spaceId }
                .map { it[SpacesUsers.userId] }
            val listUsers = Users.selectAll().where { Users.id inList listUserID}
                .map { it.toDomainUser() }
            Result.success(listUsers)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}