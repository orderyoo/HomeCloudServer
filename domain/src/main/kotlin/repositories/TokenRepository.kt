package repositories

interface TokenRepository {
    suspend fun insert(userId: Long, token: String): Result<Unit>

    suspend fun find(token: String): Result<String>

    suspend fun delete(token: String): Result<Unit>
}