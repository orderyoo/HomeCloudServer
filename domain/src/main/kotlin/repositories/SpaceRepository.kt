package repositories

import model.entities.Space

interface SpaceRepository {
    suspend fun insert(title: String, description: String?, ownerId: Long): Result<Long>

    suspend fun update(id: Long, title: String?, description: String?): Result<Unit>

    suspend fun delete(id: Long): Result<Unit>

    suspend fun findAllByOwnerId(ownerId: Long): Result<List<Space>>

    suspend fun findById(id: Long): Result<Space>
}