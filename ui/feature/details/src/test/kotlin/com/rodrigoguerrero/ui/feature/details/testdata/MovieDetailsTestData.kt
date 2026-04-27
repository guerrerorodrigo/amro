package com.rodrigoguerrero.ui.feature.details.testdata

import com.rodrigoguerrero.ui.feature.details.models.MovieDetails
import kotlinx.collections.immutable.persistentListOf
import com.rodrigoguerrero.domain.details.api.models.MovieDetails as DomainMovieDetails

internal val domainMovieDetails = DomainMovieDetails(
    title = "title",
    tagline = "tagline",
    overview = "overview",
    budget = "budget",
    imdbUrl = "imdbUrl",
    posterUrl = "posterUrl",
    voteAverage = 8.9,
    revenue = "revenue",
    runtime = 120,
    genres = listOf("genre", "another genre"),
    voteCount = 198,
    status = "status",
    releaseDate = "release date",
)

internal val expectedMovieDetails = MovieDetails(
    title = "title",
    tagline = "tagline",
    overview = "overview",
    budget = "budget",
    imdbUrl = "imdbUrl",
    posterUrl = "posterUrl",
    voteAverage = 8.9,
    revenue = "revenue",
    runtime = 120,
    genres = persistentListOf("genre", "another genre"),
    voteCount = 198,
    status = "status",
    releaseDate = "release date",
)
