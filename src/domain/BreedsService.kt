package com.doggo.domain

import com.doggo.domain.model.BreedDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface BreedsService {
    class Impl(private val client: HttpClient): BreedsService {
        override suspend fun getBreeds(): List<BreedDto> {
            return client.get {
                url("https://api.thedogapi.com/v1/breeds")
                contentType(ContentType.Application.Json)
            }
        }
        override fun close() {
            client.close()
        }
    }

    suspend fun getBreeds(): List<BreedDto>
    fun close()
}