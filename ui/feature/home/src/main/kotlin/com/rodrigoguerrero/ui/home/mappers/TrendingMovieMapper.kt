package com.rodrigoguerrero.ui.home.mappers

import com.rodrigoguerrero.ui.home.models.TrendingMovie
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.collections.immutable.toPersistentSet
import javax.inject.Inject
import com.rodrigoguerrero.domain.home.api.models.TrendingMovie as DomainTrendingMovie

internal class TrendingMovieMapper @Inject constructor(
    private val genreMapper: GenreMapper,
) {
    fun toTrendingMovies(
        trendingMovies: List<DomainTrendingMovie>,
    ): ImmutableList<TrendingMovie> = trendingMovies
        .map { toTrendingMovie(it) }
        .toPersistentList()

    private fun toTrendingMovie(trendingMovie: DomainTrendingMovie): TrendingMovie = TrendingMovie(
        title = trendingMovie.title,
        imageUrl = trendingMovie.imageUrl,
        genres = genreMapper
            .toGenres(trendingMovie.genres)
            .joinToString { it.name },
        genreIds = trendingMovie.genres.map { it.id }.toPersistentSet()
    )
}
