package com.rodrigoguerrero.domain.home.impl.mappers

import com.rodrigoguerrero.domain.home.api.models.Genre
import javax.inject.Inject
import com.rodrigoguerrero.repository.movies.api.models.Genre as RepoGenre

internal class GenresMapper @Inject constructor() {
    fun toGenres(
        genres: List<RepoGenre>,
    ): List<Genre> = genres.map { genre -> toGenre(genre) }

    fun filterGenresByIds(
        genreIds: List<Int>,
        genres: List<Genre>,
    ): List<Genre> = genreIds.mapNotNull { id -> genres.firstOrNull { it.id == id } }

    private fun toGenre(genre: RepoGenre): Genre = Genre(
        id = genre.id,
        name = genre.name,
    )
}
