package usecases.space

import model.entities.ApiKey
import model.input.SpaceUpdate
import repositories.SpaceRepository

class SpaceEditUseCase(private val spaceRepository: SpaceRepository) {
    suspend operator fun invoke(apiKey: ApiKey, space: SpaceUpdate): Result<Unit> {
        val spaceEditResult = spaceRepository.update(space.id, space.title, space.description)
        if (spaceEditResult.isFailure)
            return Result.failure(Throwable(""))

        return Result.success(Unit)
    }
}