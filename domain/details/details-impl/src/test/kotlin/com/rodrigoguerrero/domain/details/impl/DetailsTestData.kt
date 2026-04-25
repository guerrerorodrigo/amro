package com.rodrigoguerrero.domain.details.impl

import com.rodrigoguerrero.domain.details.api.models.MovieDetails
import com.rodrigoguerrero.repository.movies.api.models.Genre
import com.rodrigoguerrero.repository.movies.api.models.MovieDetails as RepoMovieDetails

internal val genre = Genre(id = 1, name = "genre")
internal val anotherGenre = Genre(id = 2, name = "another genre")

internal val repoDetails = RepoMovieDetails(
    title = "title",
    tagline = "tagline",
    overview = "overview",
    budget = 100000,
    imdbId = "imdbId",
    backdropPath = "/backdropPath",
    posterPath = "/posterPath",
    voteAverage = 8.23,
    voteCount = 10234,
    revenue = 2000000,
    runtime = 122,
    genres = listOf(genre, anotherGenre),
    status = "status",
    releaseDate = "releaseDate",
)

internal val expectedDetails = MovieDetails(
    title = "title",
    tagline = "tagline",
    overview = "overview",
    budget = "$100,000",
    imdbUrl = "https://www.imdb.com/title/imdbId",
    posterUrl = "url/posterPath",
    voteAverage = 8.23,
    voteCount = 10234,
    revenue = "$2,000,000",
    runtime = 122,
    genres = listOf(genre.name, anotherGenre.name),
    status = "status",
    releaseDate = "formated releaseDate",
)
