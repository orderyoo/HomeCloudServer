interface PasswordHasher {
    fun createHash(password: String): String
    fun verify(password: String, hash: String ): Boolean
}