package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.entities.Event
import com.example.marvelcompose.data.network.ApiClient

object EventRepository : Repository<Event>() {

    suspend fun get(): List<Event> = super.get {
        ApiClient
            .eventsService
            .getEvents(0, 100)
            .data
            .results
            .map { it.asEvent() }
    }

    suspend fun find(id: Int): Event = super.find(
        id = id,
        findActionRemote = {
            ApiClient
                .eventsService
                .findEvent(id)
                .data
                .results
                .first()
                .asEvent()
        }
    )
}