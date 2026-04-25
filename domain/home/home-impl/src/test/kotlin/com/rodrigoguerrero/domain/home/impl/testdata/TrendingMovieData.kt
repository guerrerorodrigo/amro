package com.rodrigoguerrero.domain.home.impl.testdata

import com.rodrigoguerrero.domain.home.api.models.TrendingMovie
import com.rodrigoguerrero.repository.movies.api.models.Trending as RepoTrending

internal val trending = RepoTrending(
    id = 123,
    title = "trending title",
    imageUrl = "image url path",
    genreIds = listOf(1, 2),
    popularity = 123.33,
    releaseDate = "2017-05-31"
)

internal val expectedTrending = TrendingMovie(
    id = 123,
    title = "trending title",
    imageUrl = "image url",
    genres = listOf(expectedGenre, anotherExpectedGenre),
    popularity = 123.33,
    releaseDate = 1234500000L,
)
