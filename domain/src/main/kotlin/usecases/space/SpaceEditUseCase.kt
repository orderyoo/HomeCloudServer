package usecases.space

import model.input.SpaceUpdate
import repositories.SpaceRepository
import repositories.TokenRepository

class SpaceEditUseCase(
    private val spaceRepository: SpaceRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(space: SpaceUpdate, token: String): Result<Unit> {
        val tokenVerifier = tokenRepository.verify(token)
        if(tokenVerifier.isFailure)
            return Result.failure(Throwable("token is not valid"))

        val spaceEditResult = spaceRepository.update(space.id, space.title, space.description)
        if (spaceEditResult.isFailure)
            return Result.failure(Throwable(""))

        return Result.success(Unit)
    }
}