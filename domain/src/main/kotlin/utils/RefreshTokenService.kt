package utils

interface RefreshTokenService {
    suspend fun generate(): Pair<String, String>
    suspend fun valid(hash: String, userId: Long)
    suspend fun revoke(hash: String)
}