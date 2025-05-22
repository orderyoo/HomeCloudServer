package db.tables

import model.types.ApiKeyType
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object ApiKeys: Table("Api_Keys") {
    val key = varchar("key", 255)
    val userId = long("user_id").references(Users.id, onDelete = ReferenceOption.CASCADE)
    val type = enumeration("type", ApiKeyType::class)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(key)
}