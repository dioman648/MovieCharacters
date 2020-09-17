package com.example.moviecharacters.api

import com.example.moviecharacters.model.CharactersResponse
import com.example.moviecharacters.model.Episodes
import com.example.moviecharacters.model.EpisodesItem
import com.example.moviecharacters.model.Profile
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersAPI {
    @GET("character")
    suspend fun getCharacters(
        @Query("page")
        pageNumber: Int = 1
    ): Response<CharactersResponse>

    @GET("character/{id}")
    suspend fun getProfile(@Path("id") id: Int): Response<Profile?>

    @GET("episode/{number}")
    suspend fun getEpisode(@Path("number") number: String): Response<Episodes>
}