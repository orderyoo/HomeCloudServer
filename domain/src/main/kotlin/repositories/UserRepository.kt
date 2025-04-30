package repositories

import entities.User

interface UserRepository {
    suspend fun create(userName: String, email: String, password: String): Result<Long>

    suspend fun update(userName: String?, password: String?, routeImage: String?): Result<Unit>

    suspend fun delete(id: Int): Result<Unit>

    suspend fun findById(id: Int): Result<User>

    suspend fun findByEmail(email: String): Result<User>

    suspend fun findAllBySpaceId(spaceId: Long): Result<List<User>>
}