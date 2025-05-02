package usecases

import repositories.TokenRepository
import repositories.UserRepository

class UserDeleteUseCase(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(userId: String, token: String): Result<Unit> {
        val tokenVerifier = tokenRepository.verifyAndGetOwnerId(token)
        if(tokenVerifier.isFailure)
            return Result.failure(Throwable("token is not valid"))
        val userId = tokenVerifier.getOrNull()!!

        val userDeletedResult = userRepository.delete(userId)
        if(userDeletedResult.isFailure)
            return Result.failure(Throwable("failed to delete user"))

        return Result.success(Unit)
    }
}