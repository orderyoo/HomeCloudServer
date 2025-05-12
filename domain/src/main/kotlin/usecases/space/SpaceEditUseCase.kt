package usecases.space

import model.input.SpaceUpdate
import repositories.SpaceRepository
import repositories.ApiKeyRepository

class SpaceEditUseCase(
    private val spaceRepository: SpaceRepository,
    private val apiKeyRepository: ApiKeyRepository
) {
    suspend operator fun invoke(space: SpaceUpdate, token: String): Result<Unit> {
        val apiKeyVerifierResult = apiKeyRepository.find(token)
        if(apiKeyVerifierResult.isFailure)
            return Result.failure(Throwable("token is not valid"))

        val spaceEditResult = spaceRepository.update(space.id, space.title, space.description)
        if (spaceEditResult.isFailure)
            return Result.failure(Throwable(""))

        return Result.success(Unit)
    }
}