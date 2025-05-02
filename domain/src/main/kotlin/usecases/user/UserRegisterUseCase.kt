package usecases.user

import PasswordHasher
import TokenGenerator
import model.entities.Token
import model.entities.User
import model.input.UserRegistration
import repositories.TokenRepository
import repositories.UserRepository

class UserRegisterUseCase(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val tokenGenerator: TokenGenerator,
    private val passwordHasher: PasswordHasher
) {
    suspend operator fun invoke(user: UserRegistration): Result<Token> {
        if (!User.isValidEmail(user.email))
            return Result.failure(Throwable("invalid email format"))

        val findingUserResult = userRepository.findByEmail(user.email)
        if (findingUserResult.isSuccess)
            return Result.failure(Throwable("email is already use"))

        val passwordHash = passwordHasher.createHash(user.password)

        val userCreatedId = userRepository.create(user.name, user.email, passwordHash)
        if (userCreatedId.isFailure)
            return Result.failure(Throwable("failed to create a user"))

        val userId = userCreatedId.getOrNull() ?:
            return Result.failure(Throwable("failed to create a user"))

        val token = tokenGenerator.generate()
        val tokenSaveResult = tokenRepository.insert(userId, token)
        if (tokenSaveResult.isFailure)
            return Result.failure(Throwable("couldn't link the token"))

        return tokenSaveResult
    }
}