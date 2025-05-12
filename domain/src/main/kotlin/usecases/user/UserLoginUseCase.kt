package usecases.user

import model.entities.ApiKey
import utils.HashService
import repositories.ApiKeyRepository
import repositories.UserRepository

class UserLoginUseCase(
    private val userRepository: UserRepository,
    private val apiKeyRepository: ApiKeyRepository,
    private val hashService: HashService
){
    suspend operator fun invoke(enteredPassword: String, enteredEmail: String): Result<ApiKey> {
        val userFindResult = userRepository.findByEmail(enteredEmail)
        val user = userFindResult.getOrNull() ?:
            return Result.failure(Throwable("failed to find a user"))

        val verifyResult = hashService.verify(enteredPassword, user.password)
        if (!verifyResult)
            return Result.failure(Exception("Incorrect password"))

        val apiKey = apiKeyRepository.generate(user.id)
        val apiKeySaveResult = apiKeyRepository.insert(user.id, apiKey)
        if (apiKeySaveResult.isFailure)
            return Result.failure(Throwable("couldn't link the token"))

        return Result.success(apiKey)
    }
}