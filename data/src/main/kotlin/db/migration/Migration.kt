package db.migration

import org.jetbrains.exposed.sql.transactions.transaction

object Migration {
    fun migrate() {
        transaction {
            exec("""
                CREATE TRIGGER IF NOT EXISTS update_files_updated_at
                AFTER UPDATE ON Files
                FOR EACH ROW
                WHEN NEW.updated_at = OLD.updated_at
                BEGIN
                    UPDATE Files SET updated_at = CURRENT_TIMESTAMP WHERE id = NEW.id;
                END;
                """.trimIndent()
            )
        }
    }
}