package db.mappers

import db.tables.Users
import model.entities.User
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toDomainUser() = User(
    id = this[Users.id],
    name = this[Users.userName],
    email = this[Users.email],
    password = this[Users.passwordHash],
    image = this[Users.routeImage],
    createAt = this[Users.createdAt].toString()
)