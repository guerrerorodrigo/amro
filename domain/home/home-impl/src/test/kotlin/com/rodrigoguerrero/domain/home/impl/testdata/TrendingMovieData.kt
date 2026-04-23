package com.rodrigoguerrero.domain.home.impl.testdata

import com.rodrigoguerrero.domain.home.api.models.TrendingMovie
import com.rodrigoguerrero.repository.movies.api.models.Trending as RepoTrending

internal val trending = RepoTrending(
    title = "trending title",
    imageUrl = "image url path",
    genreIds = listOf(1, 2),
)

internal val expectedTrending = TrendingMovie(
    title = "trending title",
    imageUrl = "image url",
    genres = listOf(expectedGenre, anotherExpectedGenre),
)
