package usecases

import model.entities.ApiKey
import repositories.ApiKeyRepository

class ApiKeyVerification(private val apiKeyRepository: ApiKeyRepository) {
    suspend operator fun invoke(apiKey: String): Result<ApiKey> {
        return apiKeyRepository.find(apiKey).fold(
            onSuccess = { storedApiKey ->
                Result.success(storedApiKey)
            },
            onFailure = { error->
                Result.failure(error)
            }
        )
    }
}