package usecases.user

import model.entities.User
import repositories.TokenRepository
import repositories.UserRepository

class UserGetByIdUseCase(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(userId: Long, token: String): Result<User> {
        val tokenVerifier = tokenRepository.verify(token)
        if(tokenVerifier.isFailure)
            return Result.failure(Throwable("token is not valid"))

        val userGetResult = userRepository.findById(userId)
        if (userGetResult.isFailure)
            return Result.failure(Throwable("user is not found"))

        return userGetResult
    }
}