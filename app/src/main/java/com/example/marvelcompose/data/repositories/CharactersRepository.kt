package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.model.Character
import com.example.marvelcompose.data.model.Reference
import com.example.marvelcompose.data.model.Url
import com.example.marvelcompose.data.network.ApiClient
import com.example.marvelcompose.data.network.entities.asString
import com.example.marvelcompose.data.network.entities.ApiCharacter

object CharactersRepository {

    private var charactersCache = emptyList<Character>()

    suspend fun getCharacters(): List<Character> {
        if (charactersCache.isEmpty()) {
            val result = ApiClient.charactersService.getCharacters(0, 100)
            charactersCache = result.data.results.map { it.asCharacter() }
        }
        return charactersCache
    }

    suspend fun findCharacter(characterId: Int): Character {
        val character = charactersCache.find { it.id == characterId }
        if (character != null) return character

        val result = ApiClient.charactersService.findCharacter(characterId)
        return result.data.results.first().asCharacter()
    }
}

fun ApiCharacter.asCharacter(): Character {
    val comics = comics.items.map { Reference(it.name) }
    val series = series.items.map { Reference(it.name) }
    val events = events.items.map { Reference(it.name) }
    val stories = stories.items.map { Reference(it.name) }
    val urls: List<Url> = urls.map { Url(it.type, it.url) }

    return Character(
        id = this.id,
        name = this.name,
        description = this.description,
        thumbnail = this.thumbnail.asString(),
        comics = comics,
        series = series,
        events = events,
        stories = stories,
        urls = urls
    )
}