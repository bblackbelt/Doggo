package com.doggo.adapter.model

import com.google.gson.annotations.SerializedName


data class Breed(
    val id: String,
    val name: String,
    @SerializedName("life_span")
    val lifeSpan: String,
    val temperament: String
)