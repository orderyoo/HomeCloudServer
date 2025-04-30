package repositories

import entities.Token

interface TokenRepository {
    suspend fun insert(userId: Long, token: String): Result<Unit>

    suspend fun verify(token: String): Result<Unit>

    suspend fun delete(token: String): Result<Unit>
}