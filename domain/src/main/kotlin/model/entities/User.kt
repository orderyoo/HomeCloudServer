package model.entities

data class User(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
    val image: String?,
    val createAt: String
){
    companion object { fun isValidEmail(email: String): Boolean = email.contains("@") }
}