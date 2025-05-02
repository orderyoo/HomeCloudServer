package usecases

import PasswordHasher
import TokenGenerator
import entities.Token
import entities.User
import repositories.TokenRepository
import repositories.UserRepository

class UserRegisterUseCase(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val tokenGenerator: TokenGenerator,
    private val passwordHasher: PasswordHasher
) {
    suspend operator fun invoke(userName: String, email: String, enteredPassword: String): Result<Token> {
        if (!User.isValidEmail(email))
            return Result.failure(Throwable("invalid email format"))

        val findingUserResult = userRepository.findByEmail(email)
        if (findingUserResult.isSuccess)
            return Result.failure(Throwable("email is already use"))

        val passwordHash = passwordHasher.createHash(enteredPassword)

        val userCreatedId = userRepository.create(userName, email, passwordHash)
        if (userCreatedId.isFailure)
            return Result.failure(Throwable("failed to create a user"))

        val userId = userCreatedId.getOrNull() ?: return Result.failure(Throwable("failed to create a user"))

        val token = tokenGenerator.generate()
        val tokenSaveResult = tokenRepository.insertAndReturn(userId, token)
        if (tokenSaveResult.isFailure)
            return Result.failure(Throwable("couldn't link the token"))

        return tokenSaveResult
    }
}