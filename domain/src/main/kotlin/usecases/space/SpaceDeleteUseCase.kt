package usecases.space

import repositories.SpaceRepository
import repositories.ApiKeyRepository

class SpaceDeleteUseCase(
    private val spaceRepository: SpaceRepository,
    private val apiKeyRepository: ApiKeyRepository
) {
    suspend operator fun invoke(spaceId: Long, token: String): Result<Unit> {
        val apiKeyVerifierResult = apiKeyRepository.find(token)
        if(apiKeyVerifierResult.isFailure)
            return Result.failure(Throwable("token is not valid"))

        val spaceDeleteResult = spaceRepository.delete(spaceId)
        if(spaceDeleteResult.isFailure)
            return Result.failure(Throwable("failed to delete space"))

        return Result.success(Unit)
    }
}