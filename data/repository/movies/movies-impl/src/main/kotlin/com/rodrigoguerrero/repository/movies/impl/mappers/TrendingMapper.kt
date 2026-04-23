package com.rodrigoguerrero.repository.movies.impl.mappers

import com.rodrigoguerrero.data.source.tmdb.api.models.PageResultDto
import com.rodrigoguerrero.data.source.tmdb.api.models.TrendingDto
import com.rodrigoguerrero.repository.movies.api.models.Trending
import javax.inject.Inject

internal class TrendingMapper @Inject constructor() {
    fun toTrending(
        dto: PageResultDto<TrendingDto>,
    ): List<Trending> = dto.results.map { trendingDto -> toTrending(trendingDto) }

    private fun toTrending(dto: TrendingDto): Trending = Trending(
        title = dto.title,
        imageUrl = dto.imageUrl,
        genreIds = dto.genreIds,
    )
}
