package model.entities

import model.types.SpaceType

data class Space(
    val id: Long,
    val title: String,
    val description: String?,
    val ownerId: Long,
    val type: SpaceType,
    val participants: List<User>?,
    val createAt: String
)