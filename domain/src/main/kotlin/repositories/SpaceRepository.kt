package repositories

import entities.Space

interface SpaceRepository {
    suspend fun create(title: String, description: String?, ownerId: Long): Result<Unit>

    suspend fun update(title: String?, description: String?): Result<Unit>

    suspend fun delete(id: String): Result<Unit>

    suspend fun findAllByOwnerId(ownerId: Long): Result<List<Space>>

    suspend fun findById(id: Long): Result<Space>
}