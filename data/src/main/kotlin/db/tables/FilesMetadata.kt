package db.tables

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object FilesMetadata : Table("Files_metadata") {
    val id = long("id").autoIncrement()
    val relativePath = text("relative_path").uniqueIndex()
    val ownerId = long("owner_id").references(Users.id)
    val spaceId = long("space_id").references(Spaces.id, onDelete = ReferenceOption.CASCADE)
    val name = varchar("name", 255)
    val fileSize = long("file_size").check { it greater 0 }
    val mimeType = varchar("mime_type", 128)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)

    init {
        index(true, ownerId, spaceId)
    }
}