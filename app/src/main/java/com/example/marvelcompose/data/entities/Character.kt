package com.example.marvelcompose.data.entities

data class Character(
    override val id: Int,
    override val title: String,
    override val description: String,
    override val thumbnail: String,
    override val reference: List<ReferenceList>,
    override val urls: List<Url>
): MarvelItem