package usecases.space

import model.entities.Space
import repositories.SpaceRepository
import repositories.ApiKeyRepository

class SpaceCreateUseCase(
    private val spaceRepository: SpaceRepository,
    private val apiKeyRepository: ApiKeyRepository
) {
    suspend operator fun invoke(space: Space, token: String): Result<Long> {
        val apiKeyVerifierResult = apiKeyRepository.find(token)
        val apiKey = apiKeyVerifierResult.getOrNull() ?:
            return Result.failure(Throwable("token is not valid"))

        val spaceCreateResult = spaceRepository.insert(space.title, space.description, apiKey.userId)
        if (spaceCreateResult.isFailure)
            return Result.failure(Throwable("failed to create space"))

        return spaceCreateResult
    }
}