package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.entities.Comic
import com.example.marvelcompose.data.entities.Event
import com.example.marvelcompose.data.entities.Reference
import com.example.marvelcompose.data.entities.ReferenceList
import com.example.marvelcompose.data.entities.Url
import com.example.marvelcompose.data.network.entities.ApiCharacter
import com.example.marvelcompose.data.network.entities.ApiComic
import com.example.marvelcompose.data.network.entities.ApiEvent
import com.example.marvelcompose.data.network.entities.ApiReferenceList
import com.example.marvelcompose.data.network.entities.asString

fun ApiCharacter.asCharacter(): Character = Character(
    id = id,
    title = name,
    description = description,
    thumbnail = thumbnail.asString(),
    reference = listOf(
        comics.toDomain(ReferenceList.Type.COMIC),
        events.toDomain(ReferenceList.Type.EVENT),
        series.toDomain(ReferenceList.Type.SERIES),
        stories.toDomain(ReferenceList.Type.STORY)
    ),
    urls = urls.map { Url(it.type, it.url) }
)

fun ApiComic.asComic(): Comic = Comic(
    id = id,
    title = title,
    description = description ?: "",
    thumbnail = thumbnail.asString(),
    format = format.toDomain(),
    reference = listOf(
        characters.toDomain(ReferenceList.Type.CHARACTER),
        events.toDomain(ReferenceList.Type.EVENT),
        series.toDomain(ReferenceList.Type.SERIES),
        stories.toDomain(ReferenceList.Type.STORY)
    ),
    urls = urls.map { Url(it.type, it.url) }
)

fun ApiEvent.asEvent(): Event = Event(
    id = id,
    title = title,
    description = description,
    thumbnail = thumbnail.asString(),
    reference = listOf(
        characters.toDomain(ReferenceList.Type.CHARACTER),
        comics.toDomain(ReferenceList.Type.COMIC),
        series.toDomain(ReferenceList.Type.SERIES),
        stories.toDomain(ReferenceList.Type.STORY)
    ),
    urls = urls.map { Url(it.type, it.url) }
)

private fun String.toDomain(): Comic.Format = when (this) {
    "magazine" -> Comic.Format.MAGAZINE
    "trade paperback" -> Comic.Format.TRADE_PAPERBACK
    "hardcover" -> Comic.Format.HARDCOVER
    "digest" -> Comic.Format.DIGEST
    "graphic novel" -> Comic.Format.GRAPHIC_NOVEL
    "digital comic" -> Comic.Format.DIGITAL_COMIC
    "infinite comic" -> Comic.Format.INFINITE_COMIC
    else -> Comic.Format.COMIC
}

fun Comic.Format.toStringFormat(): String = when (this) {
    Comic.Format.COMIC -> "comic"
    Comic.Format.MAGAZINE -> "magazine"
    Comic.Format.TRADE_PAPERBACK -> "trade_paperback"
    Comic.Format.HARDCOVER -> "hardcover"
    Comic.Format.DIGEST -> "digest"
    Comic.Format.GRAPHIC_NOVEL -> "graphic_novel"
    Comic.Format.DIGITAL_COMIC -> "digital_comic"
    Comic.Format.INFINITE_COMIC -> "infinite_comic"
}

private fun ApiReferenceList.toDomain(type: ReferenceList.Type): ReferenceList = ReferenceList(
    type = type,
    reference = items?.let { items.map { Reference(it.name) } } ?: emptyList()
)