package db.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Users : Table("Users") {
    val id = long("id").autoIncrement()
    val userName = varchar("user_name", 50)
    val email = varchar("email", 75).uniqueIndex()
    val passwordHash = varchar("password_hash", 255)
    val routeImage = text("route_img").nullable()
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(id)
}