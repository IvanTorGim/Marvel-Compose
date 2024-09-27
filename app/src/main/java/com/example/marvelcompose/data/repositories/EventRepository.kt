package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.entities.Event
import com.example.marvelcompose.data.entities.Result
import com.example.marvelcompose.data.network.remote.EventsService
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val service: EventsService
) : Repository<Event>() {

    suspend fun get(): Result<List<Event>> = super.get {
        service
            .getEvents(0, 100)
            .data
            .results
            .map { it.asEvent() }
    }

    suspend fun find(id: Int): Result<Event> = super.find(
        id = id,
        findActionRemote = {
            service
                .findEvent(id)
                .data
                .results
                .first()
                .asEvent()
        }
    )
}