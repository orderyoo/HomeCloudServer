package utils

interface AccessTokenService {
    suspend fun generate(userId: String): String
    suspend fun validate(): Boolean
}