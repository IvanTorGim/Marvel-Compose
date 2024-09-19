package com.example.marvelcompose.data.entities

data class Comic(
    override val id: Int,
    override val title: String,
    override val description: String,
    override val thumbnail: String,
    override val reference: List<ReferenceList>,
    override val urls: List<Url>,
    val format: Format
) : MarvelItem {
    enum class Format {
        COMIC,
        MAGAZINE,
        TRADE_PAPERBACK,
        HARDCOVER,
        DIGEST,
        GRAPHIC_NOVEL,
        DIGITAL_COMIC,
        INFINITE_COMIC,
    }
}
