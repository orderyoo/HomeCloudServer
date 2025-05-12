package usecases.user

import model.entities.User
import repositories.ApiKeyRepository
import repositories.UserRepository

class UsersGetBySpaceIdUseCase(
    private val userRepository: UserRepository,
    private val apiKeyRepository: ApiKeyRepository
) {
    suspend operator fun invoke(spaceId: Long, token: String): Result<List<User>> {
        val apiKeyVerifierResult = apiKeyRepository.find(token)
        if(apiKeyVerifierResult.isFailure)
            return Result.failure(Throwable("token is not valid"))

        val usersGetResult = userRepository.findBySpaceId(spaceId)
        if (usersGetResult.isFailure)
            return Result.failure(Throwable("user is not found"))

        return usersGetResult
    }
}