package usecases.user

import model.entities.ApiKey
import model.input.UserUpdate
import repositories.UserRepository
import utils.HashService

class UserEditUseCase(
    private val userRepository: UserRepository,
    private val passwordHasher: HashService
) {
    suspend operator fun invoke(apiKey: ApiKey, user: UserUpdate): Result<Unit> {
        var passwordHash: String? = null
        if (user.password != null)
            passwordHash = passwordHasher.createHash(user.password)

        val userEditResult = userRepository.update(apiKey.userId, user.name, passwordHash, user.routeImage)
        if (userEditResult.isFailure)
            return Result.failure(Throwable(""))

        return Result.success(Unit)
    }
}