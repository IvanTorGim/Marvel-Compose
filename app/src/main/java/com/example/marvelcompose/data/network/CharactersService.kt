package com.example.marvelcompose.data.network

import com.example.marvelcompose.data.network.entities.ApiResponse
import com.example.marvelcompose.data.network.entities.Character
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ApiResponse<Character>
}