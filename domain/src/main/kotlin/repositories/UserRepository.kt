package repositories

import entities.User

interface UserRepository {
    suspend fun create(userName: String, password: String): Result<Boolean>

    suspend fun update(userName: String?, password: String?, routeImage: String?): Result<Boolean>

    suspend fun delete(id: Int): Result<Boolean>

    suspend fun findById(id: Int): Result<User>

    suspend fun findByEmail(email: String): Result<User>

    suspend fun findAllBySpaceId(spaceId: Long): Result<List<User>>
}