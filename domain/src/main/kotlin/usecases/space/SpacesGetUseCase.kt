package usecases.space

import model.entities.ApiKey
import model.entities.Space
import repositories.SpaceRepository

class SpacesGetUseCase(private val spaceRepository: SpaceRepository) {
    suspend operator fun invoke(apiKey: ApiKey): Result<List<Space>> {
        val spacesGetResult = spaceRepository.findAllRelated(apiKey.userId)
        val spaces = spacesGetResult.getOrNull() ?:
            return Result.failure(Exception("N"))

        return Result.success(spaces)
    }
}