package usecases

import PasswordHasher
import TokenGenerator
import entities.Token
import repositories.TokenRepository
import repositories.UserRepository

class UserLoginUseCase(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val tokenGenerator: TokenGenerator,
    private val passwordHasher: PasswordHasher
){
    suspend operator fun invoke(enteredPassword: String, email: String): Result<Token> {
        val findingUser = userRepository.findByEmail(email)
        if(findingUser.isFailure)
            return Result.failure(Throwable("user was not found"))

        val verifyResult = passwordHasher.verify(enteredPassword, findingUser.getOrNull()!!.password )
        if (!verifyResult)
            return Result.failure(Throwable("invalid password"))

        val token = tokenGenerator.generate()
        val tokenSaveResult = tokenRepository.insertAndReturn(findingUser.getOrNull()!!.id, token)
        if (tokenSaveResult.isFailure)
            return Result.failure(Throwable("couldn't link the token"))

        return tokenSaveResult
    }
}