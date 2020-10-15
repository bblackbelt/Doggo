package com.doggo.setup

import com.doggo.controllers.BreedsController
import com.doggo.di.breedsModule
import com.doggo.domain.BreedsService
import com.doggo.router.breedsRouting
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.request.path
import io.ktor.routing.Routing
import org.koin.ktor.ext.inject
import org.koin.ktor.ext.koin
import org.koin.ktor.ext.modules
import org.slf4j.event.Level

private const val SERVER_PORT = 8080


@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module() {
    koin {
        modules(breedsModule)
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
    install(Routing) {
        breedsRouting(breedsController)
    }
}