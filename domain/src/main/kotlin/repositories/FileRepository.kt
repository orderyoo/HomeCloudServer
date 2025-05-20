package repositories

import java.io.InputStream
import java.nio.channels.ByteChannel

interface FileRepository {
    suspend fun save(path: String, inputStream: InputStream): Result<Unit>

    suspend fun update(path: String, inputStream: InputStream): Result<Unit>

    suspend fun delete(path: String): Result<Unit>

    suspend fun get(path: String, name: String): ByteChannel
}