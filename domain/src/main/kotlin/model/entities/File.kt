package model.entities

data class File(
    val id: Long,
    val path: String,
    val ownerId: Long,
    val spaceId: Long,
    val name: String,
    val mimeType: String,
    val size: Long,
    val createAt: String,
    val updateAt: String
)