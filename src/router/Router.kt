package com.doggo.router

import com.doggo.controllers.BreedsController
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.breedsRouting(breedsController: BreedsController) {
    route("/breeds") {
        get {
            val breeds = breedsController.getBreeds()
            println(breeds)
            call.respond(breeds)
        }
    }
}