package usecases

import PasswordHasher
import entities.User
import repositories.UserRepository

class UserLoginUseCase(
    private val userRepository: UserRepository,
    private val passwordHasher: PasswordHasher
){
    suspend operator fun invoke(inputPassword: String, email: String): Result<Boolean> {
        val data = userRepository.findByEmail(email)
        lateinit var user: User

        data.onSuccess { it ->
            user = it
        }.onFailure { error ->
            return Result.failure(error)
        }

        return Result.success(passwordHasher.verify(inputPassword, user.password))
    }
}

