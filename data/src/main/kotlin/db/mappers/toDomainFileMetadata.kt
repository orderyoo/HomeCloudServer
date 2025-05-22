package db.mappers

import db.tables.FilesMetadata
import model.entities.FileMetadata
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toDomainFileMetadata() = FileMetadata(
    id = this[FilesMetadata.id],
    relativePath = this[FilesMetadata.relativePath],
    ownerId = this[FilesMetadata.ownerId],
    spaceId = this[FilesMetadata.spaceId],
    name = this[FilesMetadata.name],
    mimeType = this[FilesMetadata.mimeType],
    size = this[FilesMetadata.fileSize],
    createAt = this[FilesMetadata.createdAt].toString(),
    updateAt = this[FilesMetadata.updatedAt].toString()
)