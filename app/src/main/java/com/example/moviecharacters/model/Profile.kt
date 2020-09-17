package com.example.moviecharacters.model

data class Profile(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationX,
    val name: String,
    val origin: OriginX,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)