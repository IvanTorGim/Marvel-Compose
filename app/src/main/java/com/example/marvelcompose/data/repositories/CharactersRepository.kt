package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.entities.Comic
import com.example.marvelcompose.data.entities.Reference
import com.example.marvelcompose.data.entities.Url
import com.example.marvelcompose.data.network.ApiClient
import com.example.marvelcompose.data.network.entities.asString
import com.example.marvelcompose.data.network.entities.ApiCharacter

object CharactersRepository : Repository<Character>() {

    suspend fun get(): List<Character> = super.get {
        ApiClient
            .charactersService
            .getCharacters(0, 100)
            .data
            .results
            .map { it.asCharacter() }
    }

    suspend fun find(id: Int): Character = super.find(
        id = id,
        findActionRemote = {
            ApiClient
                .charactersService
                .findCharacter(id)
                .data
                .results
                .first()
                .asCharacter()
        }
    )
}