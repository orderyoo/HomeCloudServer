package model.input

data class UserUpdate(
    val id: Long,
    val name: String?,
    val password: String?,
    val routeImage: String?
)