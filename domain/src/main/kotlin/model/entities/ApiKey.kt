package model.entities

data class ApiKey(
    val key: String,
    val userId: Long,
    val createdAt: String,
)