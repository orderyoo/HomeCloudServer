package model.input

import model.types.SpaceType

data class SpaceUpdate(
    val id: Long,
    val title: String?,
    val description: String?,
    val type: SpaceType
)
