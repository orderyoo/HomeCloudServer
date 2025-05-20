package db

import db.tables.FilesMetadata
import db.tables.Users
import db.tables.Spaces
import db.tables.SpacesUsers
import db.tables.ApiKeys
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        Database.connect("jdbc:sqlite:data.db", driver = "org.sqlite.JDBC")
        transaction {
            SchemaUtils.create(
                Users,
                ApiKeys,
                Spaces,
                FilesMetadata,
                SpacesUsers
            )
        }
    }
}