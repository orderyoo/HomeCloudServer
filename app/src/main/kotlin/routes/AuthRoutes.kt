package com.homecloude.routes

import entities.User
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.authRoutes() {
    route("/auth") {
        post("/register") {
            val request = call.receive<User>()
            //val response = authController.registerUser(request)
            //call.respond(HttpStatusCode.Created, response)
        }

        post("/logIn") {
            val request = call.receive<User>()
        }
    }
}