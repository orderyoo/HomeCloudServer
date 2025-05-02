package db.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object SystemAdmins: Table("system_admins") {
    val id = long("id").autoIncrement()
    val ownerId = long("owner_id").references(Users.id)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
}