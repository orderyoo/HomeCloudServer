package db

import com.zaxxer.hikari.HikariDataSource
import db.tables.FilesMetadata
import db.tables.Users
import db.tables.Spaces
import db.tables.SpacesUsers
import db.tables.ApiKeys
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    private lateinit var dataSource: HikariDataSource

    fun init(config: DatabaseConfig) {
        val cpConfig = HikariDataSource().apply {
            jdbcUrl = config.url
            driverClassName = config.driver
            maximumPoolSize = 5
            isAutoCommit = false
        }
        dataSource = HikariDataSource(cpConfig)
        Database.connect(dataSource)
    }

    fun createSchemaIfNotExists() {
        transaction {
            if (!tableExists()) {
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
    }

    private fun tableExists(): Boolean {
        return try {
            TransactionManager.current().exec("SELECT name FROM sqlite_master WHERE type='table' AND name='Users'") {
                it.next()
            } ?: false
        } catch (e: Exception) {
            false
        }
    }

    fun close() {
        dataSource.close()
    }

}