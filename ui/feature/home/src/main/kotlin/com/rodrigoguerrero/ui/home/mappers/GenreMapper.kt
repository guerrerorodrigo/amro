package com.rodrigoguerrero.ui.home.mappers

import com.rodrigoguerrero.ui.home.models.Genre
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import com.rodrigoguerrero.domain.home.api.models.Genre as DomainGenre
import javax.inject.Inject

internal class GenreMapper @Inject constructor() {

    fun toGenres(genres: List<DomainGenre>): ImmutableList<Genre> = genres
        .map { genre -> toGenre(genre) }
        .toPersistentList()

    private fun toGenre(genre: DomainGenre): Genre = Genre(
        id = genre.id,
        name = genre.name,
    )
}
