package usecases.space

import model.entities.Space
import repositories.SpaceRepository
import repositories.ApiKeyRepository

class SpacesGetUseCase(
    private val spaceRepository: SpaceRepository,
    private val apiKeyRepository: ApiKeyRepository
) {
    suspend operator fun invoke(apiKey: String): Result<List<Space>> {
        val apiKeyVerifierResult = apiKeyRepository.find(apiKey)
        val apiKey = apiKeyVerifierResult.getOrNull() ?:
            return Result.failure(Throwable("token is not valid"))

        val spacesGetResult = spaceRepository.findAllRelated(apiKey.userId)
        val spaces = spacesGetResult.getOrNull() ?:
            return Result.failure(Exception("N"))

        return Result.success(spaces)
    }
}