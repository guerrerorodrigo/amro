package com.rodrigoguerrero.ui.home.testdata

import com.rodrigoguerrero.ui.home.models.TrendingMovie
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf
import com.rodrigoguerrero.domain.home.api.models.TrendingMovie as DomainTrendingMovie

internal val domainTrendingMovie = DomainTrendingMovie(
    id = 1,
    title = "title",
    imageUrl = "image url",
    genres = listOf(domainGenre, domainGenre.copy(id = 2, name = "another genre")),
    popularity = 8.33,
    releaseDate = 1234000000,
)

internal val expectedTrendingMovie = TrendingMovie(
    id = 1,
    title = "title",
    imageUrl = "image url",
    genres = "genre name, another genre",
    genreIds = persistentSetOf(1, 2),
    popularity = 8.33,
    releaseDate = 2,
)

internal val uiTrendingMovie = TrendingMovie(
    id = 2,
    title = "another title",
    imageUrl = "image url",
    genres = "genre name, another genre",
    genreIds = persistentSetOf(1),
    popularity = 7.12,
    releaseDate = 1,
)

internal val anotherUiTrendingMovie = TrendingMovie(
    id = 3,
    title = "movie title",
    imageUrl = "image url",
    genres = "genre name, another genre",
    genreIds = persistentSetOf(3, 4),
    popularity = 5.1,
    releaseDate = 3,
)

internal val trendingMoviesSortedByTitleAscending = persistentListOf(
    uiTrendingMovie,
    anotherUiTrendingMovie,
    expectedTrendingMovie,
)

internal val trendingMoviesSortedByTitleDescending = persistentListOf(
    expectedTrendingMovie,
    anotherUiTrendingMovie,
    uiTrendingMovie,
)

internal val trendingMoviesSortedByPopularityAscending = persistentListOf(
    anotherUiTrendingMovie,
    uiTrendingMovie,
    expectedTrendingMovie,
)

internal val trendingMoviesSortedByPopularityDescending = persistentListOf(
    expectedTrendingMovie,
    uiTrendingMovie,
    anotherUiTrendingMovie,
)

internal val trendingMoviesSortedByReleaseDateAscending = persistentListOf(
    uiTrendingMovie,
    expectedTrendingMovie,
    anotherUiTrendingMovie,
)

internal val trendingMoviesSortedByReleaseDateDescending = persistentListOf(
    anotherUiTrendingMovie,
    expectedTrendingMovie,
    uiTrendingMovie,
)
