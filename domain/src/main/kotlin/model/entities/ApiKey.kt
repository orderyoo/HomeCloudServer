package model.entities

import model.types.ApiKeyType

data class ApiKey(
    val key: String,
    val userId: Long,
    val type: ApiKeyType,
    val createdAt: String,
)