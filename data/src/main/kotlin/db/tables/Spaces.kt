package db.tables

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Spaces : Table("Spaces") {
    val id = long("id").autoIncrement()
    val title = varchar("title", 100)
    val description = varchar("description", 300).nullable()
    val ownerId = long("owner_id").references(Users.id, onDelete = ReferenceOption.SET_NULL)
    val isPrivate = bool("is_private").default(true)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(id)
}