package com.rodrigoguerrero.repository.movies.impl.testdata

import com.rodrigoguerrero.data.source.tmdb.api.models.PageResultDto
import com.rodrigoguerrero.data.source.tmdb.api.models.TrendingDto
import com.rodrigoguerrero.repository.movies.api.models.Trending

internal val trendingDto = TrendingDto(
    id = 1234,
    title = "title",
    imageUrl = "image url",
    genreIds = listOf(123),
    popularity = 8.43,
    releaseDate = "release date",
)

internal val trendingPageResultDto = PageResultDto(
    results = listOf(trendingDto),
)

internal val emptyTrendingPageResultDto = PageResultDto<TrendingDto>(
    results = listOf(),
)

internal val expectedTrending = Trending(
    id = 1234,
    title = "title",
    imageUrl = "image url",
    genreIds = listOf(123),
    popularity = 8.43,
    releaseDate = "release date",
)
