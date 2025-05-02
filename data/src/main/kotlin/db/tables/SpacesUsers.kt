package db.tables

import model.entities.SpaceUserRole
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object SpacesUsers : Table("Spaces_Users") {
    val id = long("id").autoIncrement()
    val spaceId = long("space_id").references(Spaces.id, onDelete = ReferenceOption.CASCADE)
    val userId = long("user_id").references(Users.id)
    val role = enumeration("role", SpaceUserRole::class)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(id)

    init {
        uniqueIndex(spaceId, userId)
    }
}