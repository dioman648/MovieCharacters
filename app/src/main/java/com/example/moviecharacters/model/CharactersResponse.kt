package com.example.moviecharacters.model

data class CharactersResponse(
    val info: Info,
    val results: List<Result>
)