package usecases.space

import model.entities.Space
import repositories.SpaceRepository
import repositories.TokenRepository

class SpaceCreateUseCase(
    private val spaceRepository: SpaceRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(space: Space, token: String): Result<Long> {
        val tokenVerifier = tokenRepository.verify(token)
        val userId = tokenVerifier.getOrNull() ?:
            return Result.failure(Throwable("token is not valid"))

        val spaceCreateResult = spaceRepository.create(space.title, space.description, userId)
        if (spaceCreateResult.isFailure)
            return Result.failure(Throwable("failed to create space"))

        return spaceCreateResult
    }
}