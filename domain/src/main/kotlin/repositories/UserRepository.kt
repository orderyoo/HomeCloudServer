package repositories

interface UserRepository {
    fun createUser()
    fun findById()
    fun deleteUser()
}