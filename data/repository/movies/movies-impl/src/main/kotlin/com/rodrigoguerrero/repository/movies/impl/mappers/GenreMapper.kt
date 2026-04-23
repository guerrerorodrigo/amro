package com.rodrigoguerrero.repository.movies.impl.mappers

import com.rodrigoguerrero.data.source.tmdb.api.models.GenreDto
import com.rodrigoguerrero.repository.movies.api.models.Genre
import javax.inject.Inject

internal class GenreMapper @Inject constructor() {
    fun toGenres(genresDto: List<GenreDto>): List<Genre> = genresDto.map { toGenre(it) }

    private fun toGenre(dto: GenreDto): Genre = Genre(
        id = dto.id,
        name = dto.name,
    )
}
