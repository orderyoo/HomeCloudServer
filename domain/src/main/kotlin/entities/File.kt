package entities

data class File(
    val id: Int,
    val route: String,
    val ownerId: Int,
    val filename: String,
    val mimeType: String,
    val fileSize: Long,
    val createAt: String,
    val updateAt: String
)