package usecases

import PasswordHasher
import repositories.UserRepository

class UserRegisterUseCase(
    private val userRepository: UserRepository,
    private val passwordHasher: PasswordHasher
) {
    suspend operator fun invoke(userName: String, password: String): Result<Boolean> {
        val passwordHash = passwordHasher.createHash(password)
        val state = userRepository.create(userName, passwordHash)

        return if(state.isSuccess)
            state
        else
            Result.success(false)
    }
}