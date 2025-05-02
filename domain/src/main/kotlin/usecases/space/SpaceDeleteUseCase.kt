package usecases.space

import repositories.SpaceRepository
import repositories.TokenRepository

class SpaceDeleteUseCase(
    private val spaceRepository: SpaceRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(spaceId: Long, token: String): Result<Unit> {
        val tokenVerifier = tokenRepository.verify(token)
        if(tokenVerifier.isFailure)
            return Result.failure(Throwable("token is not valid"))

        val spaceDeleteResult = spaceRepository.delete(spaceId)
        if(spaceDeleteResult.isFailure)
            return Result.failure(Throwable("failed to delete space"))

        return Result.success(Unit)
    }
}