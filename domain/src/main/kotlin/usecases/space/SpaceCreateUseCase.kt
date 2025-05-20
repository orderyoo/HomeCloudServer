package usecases.space

import model.entities.ApiKey
import model.entities.Space
import repositories.SpaceRepository

class SpaceCreateUseCase(private val spaceRepository: SpaceRepository) {
    suspend operator fun invoke(apiKey: ApiKey, space: Space): Result<Long> {
        val spaceCreateResult = spaceRepository.insert(space.title, space.description, apiKey.userId)
        if (spaceCreateResult.isFailure)
            return Result.failure(Throwable("failed to create space"))

        return spaceCreateResult
    }
}