package model.input

data class FileMetadataUpdate(
    val path: String?,
    val ownerId: Long,
    val spaceId: Long,
    val name: String?,
    val mimeType: String?,
    val size: Long?
)