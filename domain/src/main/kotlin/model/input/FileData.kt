package model.input

import java.io.InputStream

data class FileData(
    val inputStream: InputStream,
    val fileName: String,
    val relativePath: String,
    val spaceId: Long
)