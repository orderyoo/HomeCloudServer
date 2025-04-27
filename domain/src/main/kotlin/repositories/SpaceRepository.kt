package repositories

import entities.Space

interface SpaceRepository {
    suspend fun findAllByOwnerId(ownerId: Long): Result<List<Space>>

    suspend fun findById(id: Long): Result<Space>
}