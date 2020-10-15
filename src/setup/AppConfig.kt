package com.doggo.setup

import com.doggo.controllers.BreedsController
import com.doggo.controllers.DoggoController
import com.doggo.di.breedsModule
import com.doggo.di.doggosModule
import com.doggo.di.networkModule
import com.doggo.router.breedsRouting
import com.doggo.router.doggosRouting
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.request.path
import io.ktor.routing.Routing
import org.koin.ktor.ext.inject
import org.koin.ktor.ext.koin
import org.slf4j.event.Level

private const val SERVER_PORT = 8080


@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    koin {
        modules(breedsModule, networkModule, doggosModule)
    }
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
    install(ContentNegotiation) {
        gson {
        }
    }
    val breedsController by inject<BreedsController>()
    val doggoController by inject<DoggoController>()
    install(Routing) {
        breedsRouting(breedsController)
        doggosRouting(doggoController)
    }
}