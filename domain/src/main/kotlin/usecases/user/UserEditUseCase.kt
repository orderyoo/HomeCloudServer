package usecases.user

import PasswordHasher
import model.input.UserUpdate
import repositories.TokenRepository
import repositories.UserRepository

class UserEditUseCase(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val passwordHasher: PasswordHasher
) {
    suspend operator fun invoke(user: UserUpdate, token: String): Result<Unit> {
        val tokenVerifier = tokenRepository.verify(token)
        val userId = tokenVerifier.getOrNull() ?:
            return Result.failure(Throwable("token is not valid"))

        var passwordHash: String? = null
        if (user.password != null)
            passwordHash = passwordHasher.createHash(user.password)

        val userEditResult = userRepository.update(userId, user.name, passwordHash, user.routeImage)
        if (userEditResult.isFailure)
            return Result.failure(Throwable(""))

        return Result.success(Unit)
    }
}