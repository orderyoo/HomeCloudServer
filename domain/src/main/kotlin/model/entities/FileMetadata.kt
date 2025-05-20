package model.entities

data class FileMetadata(
    val id: Long,
    val relativePath: String,
    val ownerId: Long,
    val spaceId: Long,
    val name: String,
    val mimeType: String,
    val size: Long,
    val createAt: String,
    val updateAt: String
)