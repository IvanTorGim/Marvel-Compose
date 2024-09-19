package com.example.marvelcompose.data.entities

interface MarvelItem {
    val id: Int
    val title: String
    val description: String
    val thumbnail: String
    val reference: List<ReferenceList>
    val urls: List<Url>
}