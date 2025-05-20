package mappers

import db.tables.ApiKeys
import model.entities.ApiKey
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toDomainApiKey() = ApiKey (
    key = this[ApiKeys.key],
    userId =  this[ApiKeys.userId],
    createdAt = this[ApiKeys.createdAt].toString(),
    description = this[ApiKeys.key]
)