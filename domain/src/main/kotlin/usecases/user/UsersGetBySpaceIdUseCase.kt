package usecases.user

import model.entities.User
import repositories.UserRepository

class UsersGetBySpaceIdUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(spaceId: Long): Result<List<User>> {
        val usersGetResult = userRepository.findBySpaceId(spaceId)
        if (usersGetResult.isFailure)
            return Result.failure(Throwable("user is not found"))

        return usersGetResult
    }
}