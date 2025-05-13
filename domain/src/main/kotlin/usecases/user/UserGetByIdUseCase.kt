package usecases.user

import model.entities.User
import repositories.UserRepository

class UserGetByIdUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(userId: Long): Result<User> {
        val userGetResult = userRepository.findById(userId)
        if (userGetResult.isFailure)
            return Result.failure(Throwable("user is not found"))

        return userGetResult
    }
}