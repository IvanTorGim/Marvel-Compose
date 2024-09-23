package com.example.marvelcompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.marvelcompose.data.entities.Event
import com.example.marvelcompose.data.repositories.EventRepository
import com.example.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.example.marvelcompose.ui.screens.common.MarvelItemListScreen

@Composable
fun EventsScreen(onClick: (Event) -> Unit) {
    var eventsState by remember { mutableStateOf(emptyList<Event>()) }
    LaunchedEffect(Unit) { eventsState = EventRepository.get() }
    MarvelItemListScreen(eventsState, onClick)
}

@Composable
fun EventDetailScreen(eventId: Int) {
    var eventState by remember { mutableStateOf<Event?>(null) }
    LaunchedEffect(Unit) { eventState = EventRepository.find(eventId) }
    eventState?.let {
        MarvelItemDetailScreen(it)
    }
}
