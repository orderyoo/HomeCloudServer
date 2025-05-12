package usecases.user

import model.entities.User
import repositories.ApiKeyRepository
import repositories.UserRepository

class UserGetByIdUseCase(
    private val userRepository: UserRepository,
    private val apiKeyRepository: ApiKeyRepository
) {
    suspend operator fun invoke(userId: Long, token: String): Result<User> {
        val apiKeyVerifierResult = apiKeyRepository.find(token)
        if(apiKeyVerifierResult.isFailure)
            return Result.failure(Throwable("token is not valid"))

        val userGetResult = userRepository.findById(userId)
        if (userGetResult.isFailure)
            return Result.failure(Throwable("user is not found"))

        return userGetResult
    }
}