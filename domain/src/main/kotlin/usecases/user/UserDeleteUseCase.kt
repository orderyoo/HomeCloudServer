package usecases.user

import repositories.ApiKeyRepository
import repositories.UserRepository

class UserDeleteUseCase(
    private val userRepository: UserRepository,
    private val apiKeyRepository: ApiKeyRepository
) {
    suspend operator fun invoke(userId: String, token: String): Result<Unit> {
        val apiKeyVerifier = apiKeyRepository.find(token)
        val apiKey = apiKeyVerifier.getOrNull() ?:
            return Result.failure(Throwable("token is not valid"))

        val userDeletedResult = userRepository.delete(apiKey.userId)
        if(userDeletedResult.isFailure)
            return Result.failure(Throwable("failed to delete user"))

        return Result.success(Unit)
    }
}