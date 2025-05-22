package repositories

import db.tables.ApiKeys
import kotlinx.datetime.Clock
import kotlinx.datetime.toLocalDateTime
import mappers.toDomainApiKey
import model.entities.ApiKey
import model.types.ApiKeyType
import model.types.SpaceUserRole
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.security.SecureRandom
import java.util.Base64

class ApiKeyRepositoryImpl: ApiKeyRepository {

    companion object {
        private const val RANDOM_KEY_LENGTH = 32
        private val secureRandom = SecureRandom()
    }

    override suspend fun generate(userId: Long): ApiKey {
        val randomBytes = ByteArray(RANDOM_KEY_LENGTH)
        secureRandom.nextBytes(randomBytes)
        val key = Base64.getUrlEncoder().encodeToString(randomBytes)
        return ApiKey (
            key = key,
            userId = userId,
            type = ApiKeyType.USER,
            createdAt = ""
        )
    }

    override suspend fun insert(apiKey: ApiKey): Result<Unit> = transaction {
        try {
            ApiKeys.insert {
                it[ApiKeys.key] = apiKey.key
                it[ApiKeys.userId] = userId
                it[ApiKeys.type] = apiKey.type
                it[ApiKeys.createdAt] = Clock.System.now().toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault())
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun find(apiKey: String): Result<ApiKey> = transaction {
        try {
            val findingApiKey = ApiKeys.selectAll().where { ApiKeys.key eq apiKey }
                .map { it.toDomainApiKey() }
                .single()
            Result.success(findingApiKey)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun delete(apiKey: String): Result<Unit> = transaction {
        try {
            ApiKeys.deleteWhere {ApiKeys.key eq apiKey }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}