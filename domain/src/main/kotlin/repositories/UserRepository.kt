package repositories

import model.entities.User

interface UserRepository {
    suspend fun insert(userName: String, email: String, password: String): Result<Long>

    suspend fun update(id: Long, newName: String?, newPassword: String?, newRouteImage: String?): Result<Unit>

    suspend fun delete(id: Long): Result<Unit>

    suspend fun findById(id: Long): Result<User>

    suspend fun findByEmail(email: String): Result<User>

    suspend fun findBySpaceId(spaceId: Long): Result<List<User>>
}