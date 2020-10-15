package com.doggo.router

import com.doggo.controllers.BreedsController
import com.doggo.controllers.DoggoController
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

fun Routing.doggosRouting(doggoController: DoggoController) {
    route("/doggos") {
        get {
            val page = call.parameters["page"]?.toInt() ?: 1
            val limit = call.parameters["limit"]?.toInt() ?: 25
            val breedId = call.parameters["breed_id"]
            val doggos = doggoController.getDoggos(page, limit, breedId)
            call.respond(doggos)
        }
    }
}