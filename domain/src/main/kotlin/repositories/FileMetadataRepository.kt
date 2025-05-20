package repositories

import model.entities.FileMetadata

interface FileMetadataRepository {
    suspend fun insert(path: String, ownerId: Long, spaceID: String): Result<Long>

    suspend fun update(id: Long): Result<Unit>

    suspend fun delete(id: Long): Result<Unit>

    suspend fun findBySpaceId(offset: Int, count: Int, spaceId: Long): Result<List<FileMetadata>>
}