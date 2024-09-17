package com.example.marvelcompose.data.network.entities

data class Thumbnail(
    val extension: String,
    val path: String
)

fun Thumbnail.asString() = "$path.$extension".replace("http", "https")