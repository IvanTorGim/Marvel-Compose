package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.model.Character
import com.example.marvelcompose.data.network.ApiClient
import com.example.marvelcompose.data.network.entities.asString

object CharactersRepository {

    suspend fun getCharacters(): List<Character>{
        val result = ApiClient.charactersService.getCharacters(0, 100)
        return result.data.results.map {
            Character(
                id = it.id,
                name = it.name,
                description = it.description,
                thumbnail = it.thumbnail.asString()
            )
        }
    }
}