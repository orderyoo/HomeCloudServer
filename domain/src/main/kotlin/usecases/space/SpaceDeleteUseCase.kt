package usecases.space

import model.entities.ApiKey
import repositories.SpaceRepository

class SpaceDeleteUseCase(private val spaceRepository: SpaceRepository) {
    suspend operator fun invoke(apiKey: ApiKey, spaceId: Long): Result<Unit> {
        val spaceDeleteResult = spaceRepository.delete(spaceId)
        if(spaceDeleteResult.isFailure)
            return Result.failure(Throwable("failed to delete space"))

        return Result.success(Unit)
    }
}