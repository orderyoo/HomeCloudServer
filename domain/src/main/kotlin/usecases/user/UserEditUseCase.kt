package usecases.user

import utils.HashService
import model.input.UserUpdate
import repositories.ApiKeyRepository
import repositories.UserRepository

class UserEditUseCase(
    private val userRepository: UserRepository,
    private val apiKeyRepository: ApiKeyRepository,
    private val passwordHasher: HashService
) {
    suspend operator fun invoke(user: UserUpdate, apiKey: String): Result<Unit> {
        val apiKeyVerifierResult = apiKeyRepository.find(apiKey)
        val apiKey = apiKeyVerifierResult.getOrNull() ?:
            return Result.failure(Throwable("token is not valid"))

        var passwordHash: String? = null
        if (user.password != null)
            passwordHash = passwordHasher.createHash(user.password)

        val userEditResult = userRepository.update(apiKey.userId, user.name, passwordHash, user.routeImage)
        if (userEditResult.isFailure)
            return Result.failure(Throwable(""))

        return Result.success(Unit)
    }
}