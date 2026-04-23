package com.rodrigoguerrero.repository.movies.impl.testdata

import com.rodrigoguerrero.data.source.tmdb.api.models.GenreDto
import com.rodrigoguerrero.data.source.tmdb.api.models.GenreResultDto
import com.rodrigoguerrero.repository.movies.api.models.Genre

internal val genreDto = GenreDto(
    id = 123,
    name = "genre name",
)

internal val anotherGenreDto = GenreDto(
    id = 124,
    name = "another genre name",
)

internal val genresResultDto = GenreResultDto(
    genres = listOf(genreDto, anotherGenreDto),
)

internal val expectedGenre = Genre(
    id = 123,
    name = "genre name",
)

internal val anotherExpectedGenre = Genre(
    id = 124,
    name = "another genre name",
)
