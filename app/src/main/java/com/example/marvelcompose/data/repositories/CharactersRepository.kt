package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.model.Character
import com.example.marvelcompose.data.model.Reference
import com.example.marvelcompose.data.network.ApiClient
import com.example.marvelcompose.data.network.entities.asString
import com.example.marvelcompose.data.network.entities.Character as NetworkCharacter

object CharactersRepository {

    suspend fun getCharacters(): List<Character> {
        val result = ApiClient.charactersService.getCharacters(0, 100)
        return result.data.results.map { it.asCharacter() }
    }

    suspend fun findCharacter(characterId: Int): Character {
        val result = ApiClient.charactersService.findCharacter(characterId)
        return result.data.results.first().asCharacter()
    }
}

fun NetworkCharacter.asCharacter(): Character {
    val comics = comics.items.map { Reference(it.name) }
    val series = series.items.map { Reference(it.name) }
    val events = events.items.map { Reference(it.name) }
    val stories = stories.items.map { Reference(it.name) }

    return Character(
        id = this.id,
        name = this.name,
        description = this.description,
        thumbnail = this.thumbnail.asString(),
        comics = comics,
        series = series,
        events = events,
        stories = stories
    )
}