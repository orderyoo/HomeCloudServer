package repositories

import model.entities.ApiKey

interface ApiKeyRepository {
    suspend fun generate(userId: Long): ApiKey

    suspend fun insert(apiKey: ApiKey): Result<Unit>

    suspend fun find(apiKey: String): Result<ApiKey>

    suspend fun delete(apiKey: String): Result<Unit>
}