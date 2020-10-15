package com.doggo.adapter

import com.doggo.adapter.model.Breed
import com.doggo.adapter.model.Doggo
import com.doggo.domain.model.BreedDto
import domain.model.DoggoDto

interface DoggoAdapter {

    fun getDoggo(doggoDto: DoggoDto): Doggo

    class Impl : DoggoAdapter {
        override fun getDoggo(doggoDto: DoggoDto): Doggo = with(doggoDto) {
            Doggo(height, id, url, width, breeds.firstOrNull()?.name ?: "")
        }
    }
}