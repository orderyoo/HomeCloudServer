package usecases

import PasswordHasher
import repositories.TokenRepository
import repositories.UserRepository

class UserEditUseCase(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val passwordHasher: PasswordHasher
) {
    suspend operator fun invoke(newName: String?, newPassword: String?, newRouteImage: String?, token: String): Result<Unit>{
        val tokenVerifier = tokenRepository.verifyAndGetOwnerId(token)
        if(tokenVerifier.isFailure)
            return Result.failure(Throwable("token is not valid"))
        val userId = tokenVerifier.getOrNull()!!

        var newPasswordHash: String? = null
        newPassword?.let { newPasswordHash = passwordHasher.createHash(it) }

        val userEditResult = userRepository.update(userId, newName, newPasswordHash, newRouteImage)
        if (userEditResult.isFailure)
            return Result.failure(Throwable(""))

        return Result.success(Unit)
    }
}