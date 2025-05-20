package mappers


import db.tables.Spaces
import model.entities.Space
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toDomainSpace() = Space(
    id = this[Spaces.id],
    title = this[Spaces.title],
    description = this[Spaces.description],
    ownerId = this[Spaces.ownerId],
    type = this[Spaces.type],
    participants = null,
    createAt = this[Spaces.createdAt].toString()
)