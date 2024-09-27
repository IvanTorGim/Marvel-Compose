package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.entities.Result
import com.example.marvelcompose.data.network.remote.CharactersService
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val service: CharactersService
) : Repository<Character>() {

    suspend fun get(): Result<List<Character>> = super.get {
        service
            .getCharacters(0, 100)
            .data
            .results
            .map { it.asCharacter() }
    }

    suspend fun find(id: Int): Result<Character> = super.find(
        id = id,
        findActionRemote = {
            service
                .findCharacter(id)
                .data
                .results
                .first()
                .asCharacter()
        }
    )
}