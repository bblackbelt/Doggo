package com.doggo.domain

import domain.model.DoggoDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType


interface DoggoService {
    class Impl constructor(private val client: HttpClient) : DoggoService {

        override suspend fun getDoggos(page: Int, limit: Int, breedId: String?): List<DoggoDto> {
            var url = "https://api.thedogapi.com/v1/images/search?page=$page&limit=$limit"
            breedId?.let { url += "breed_id=$breedId" }
            return client.get {
                url(url)
                contentType(ContentType.Application.Json)
            }
        }

        override fun close() {
            client.close()
        }
    }

    suspend fun getDoggos(page: Int, limit: Int = 25, breedId: String? = null): List<DoggoDto>
    fun close()
}