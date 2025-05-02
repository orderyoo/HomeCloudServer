package repositories

import model.entities.Token

interface TokenRepository {
    suspend fun insert(userId: Long, token: String): Result<Token>

    suspend fun verify(token: String): Result<Long>

    suspend fun delete(token: String): Result<Unit>
}