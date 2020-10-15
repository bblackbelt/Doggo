package com.doggo.domain

import com.doggo.domain.model.BreedModelDto
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.BrowserUserAgent
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface BreedsService {
    class Impl: BreedsService {
        private val client: HttpClient by lazy {
            HttpClient(Apache) {
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
            }
        }

        override suspend fun getBreeds(): List<BreedModelDto> {
            return client.get {
                url("https://api.thedogapi.com/v1/breeds")
                contentType(ContentType.Application.Json)
            }
        }
    }

    suspend fun getBreeds(): List<BreedModelDto>
}