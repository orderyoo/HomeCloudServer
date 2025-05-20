package model.input

data class FileMetadataInsert(
    val relativePath: String,
    val ownerId: Long,
    val spaceId: Long,
    val name: String,
    val mimeType: String,
    val size: Long,
)