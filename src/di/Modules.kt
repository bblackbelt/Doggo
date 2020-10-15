package com.doggo.di

import com.doggo.adapter.BreedsAdapter
import com.doggo.adapter.DoggoAdapter
import com.doggo.controllers.BreedsController
import com.doggo.controllers.DoggoController
import com.doggo.domain.BreedsService
import com.doggo.domain.DoggoService
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.BrowserUserAgent
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.header
import org.koin.dsl.module

val breedsModule = module {
    factory<BreedsService> { BreedsService.Impl(get()) }
    factory<BreedsController> { BreedsController.Impl(get(), get()) }
    factory<BreedsAdapter> { BreedsAdapter.Impl() }
}

val doggosModule = module {
    factory<DoggoService> { DoggoService.Impl(get()) }
    factory<DoggoController> { DoggoController.Impl(get(), get()) }
    factory<DoggoAdapter> { DoggoAdapter.Impl() }
}

val networkModule = module {
    factory { HttpClient(Apache) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        install(Logging) {
            level = LogLevel.HEADERS
        }
        BrowserUserAgent() // install default browser-like user-agent
        // install(UserAgent) { agent = "some user agent" }
        defaultRequest {
            header("x-api-key", "2f5b39cb-93c5-47f5-9507-c1e9d2b94dd0")
        }
    } }
}