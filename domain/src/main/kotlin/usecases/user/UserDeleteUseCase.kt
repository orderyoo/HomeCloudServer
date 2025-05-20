package usecases.user

import model.entities.ApiKey
import repositories.UserRepository

class UserDeleteUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(apiKey: ApiKey, userId: String): Result<Unit> {
        val userDeletedResult = userRepository.delete(apiKey.userId)
        if(userDeletedResult.isFailure)
            return Result.failure(Throwable("failed to delete user"))

        return Result.success(Unit)
    }
}