package usecases.user

import model.entities.ApiKey
import utils.HashService
import model.entities.User
import model.input.UserRegistration
import repositories.ApiKeyRepository
import repositories.UserRepository

class UserRegisterUseCase(
    private val userRepository: UserRepository,
    private val apiKeyRepository: ApiKeyRepository,
    private val hashService: HashService
) {
    suspend operator fun invoke(user: UserRegistration): Result<ApiKey> {
        if (!User.isValidEmail(user.email))
            return Result.failure(Throwable("invalid email format"))

        val userFindResult = userRepository.findByEmail(user.email)
        if (userFindResult.isSuccess)
            return Result.failure(Throwable("email is already use"))

        val passwordHash = hashService.createHash(user.password)

        val createdUserId = userRepository.insert(user.name, user.email, passwordHash)
        val userId = createdUserId.getOrNull() ?:
            return Result.failure(Throwable("failed to create a user"))

        val apiKey = apiKeyRepository.generate(userId)
        val apiKeySaveResult = apiKeyRepository.insert(apiKey)
        if (apiKeySaveResult.isFailure)
            return Result.failure(Throwable("couldn't link the token"))

        return Result.success(apiKey)
    }
}