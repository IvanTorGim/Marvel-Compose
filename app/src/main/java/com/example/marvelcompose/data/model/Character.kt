package com.example.marvelcompose.data.model

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String,
    val comics: List<Reference>,
    val events: List<Reference>,
    val stories: List<Reference>,
    val series: List<Reference>,
    val urls: List<Url>
)