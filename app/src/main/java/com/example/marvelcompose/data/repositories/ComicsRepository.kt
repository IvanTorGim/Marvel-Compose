package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.entities.Comic
import com.example.marvelcompose.data.entities.Result
import com.example.marvelcompose.data.entities.tryCall
import com.example.marvelcompose.data.network.remote.ComicsService
import javax.inject.Inject

class ComicsRepository @Inject constructor(
    private val service: ComicsService
) {

    suspend fun get(format: Comic.Format): Result<List<Comic>> = tryCall {
        service
            .getComics(0, 20, format.toStringFormat())
            .data
            .results
            .map { it.asComic() }
    }

    suspend fun find(id: Int): Result<Comic> = tryCall {
        service
            .findComic(id)
            .data
            .results
            .first()
            .asComic()
    }
}