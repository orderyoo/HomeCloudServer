package usecases.user

import model.entities.User
import repositories.TokenRepository
import repositories.UserRepository

class UserGetBySpaceIdUseCase(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(spaceId: Long, token: String): Result<List<User>> {
        val tokenVerifier = tokenRepository.verify(token)
        if(tokenVerifier.isFailure)
            return Result.failure(Throwable("token is not valid"))

        val usersGetResult = userRepository.findAllBySpaceId(spaceId)
        if (usersGetResult.isFailure)
            return Result.failure(Throwable("user is not found"))

        return usersGetResult
    }
}