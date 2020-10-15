package com.doggo.domain.model

import com.google.gson.annotations.SerializedName

data class BreedModelDto(
    val id: String,
    val name: String,
    val breed_group: String?,
    @SerializedName("life_span")
    val lifeSpan: String,
    val temperament: String?
)