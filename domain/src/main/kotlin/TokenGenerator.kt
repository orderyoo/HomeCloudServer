interface TokenGenerator {
    suspend fun generate(): String
}