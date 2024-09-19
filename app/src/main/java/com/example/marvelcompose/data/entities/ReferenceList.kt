package com.example.marvelcompose.data.entities

data class ReferenceList(
    val type: Type,
    val reference: List<Reference>
) {
    enum class Type {
        CHARACTER,
        COMIC,
        STORY,
        EVENT,
        SERIES
    }
}
