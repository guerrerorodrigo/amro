package com.rodrigoguerrero.repository.movies.impl.testdata

import com.rodrigoguerrero.data.source.tmdb.api.models.MovieDetailsDto
import com.rodrigoguerrero.repository.movies.api.models.MovieDetails

internal val movieDetailsDto = MovieDetailsDto(
    title = "title",
    tagline = "tagline",
    overview = "overview",
    budget = 20000000,
    imdbId = "imdb id",
    backdropPath = "backdrop path",
    posterPath = "poster path",
    voteAverage = 89.3,
    revenue = 12345677,
    runtime = 120,
    genres = listOf(genreDto),
    voteCount = 534,
    status = "status",
    releaseDate = "release date",
)

internal val expectedMovieDetails = MovieDetails(
    title = "title",
    tagline = "tagline",
    overview = "overview",
    budget = 20000000,
    imdbId = "imdb id",
    backdropPath = "backdrop path",
    posterPath = "poster path",
    voteAverage = 89.3,
    revenue = 12345677,
    runtime = 120,
    genres = listOf(expectedGenre),
    voteCount = 534,
    status = "status",
    releaseDate = "release date",
)
