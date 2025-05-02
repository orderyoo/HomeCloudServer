package repositories

import entities.Token

interface TokenRepository {
    suspend fun insert(userId: Long, token: String): Result<Unit>

    suspend fun insertAndReturn(userId: Long, token: String): Result<Token>

    suspend fun verify(token: String): Result<Unit>

    suspend fun verifyAndGetOwnerId(token: String): Result<Long>

    suspend fun delete(token: String): Result<Unit>
}