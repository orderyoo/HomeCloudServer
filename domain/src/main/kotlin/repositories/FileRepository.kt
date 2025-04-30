package repositories

import entities.File

interface FileRepository {
    suspend fun findAll(offset: Int, count: Int, ownerId: Long, spaceId: Long): Result<File>
}