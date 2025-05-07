package utils

interface HashService {
    fun createHash(value: String): String
    fun verify(value: String, hash: String ): Boolean
}