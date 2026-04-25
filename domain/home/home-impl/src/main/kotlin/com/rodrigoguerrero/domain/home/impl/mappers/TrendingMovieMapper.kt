package com.rodrigoguerrero.domain.home.impl.mappers

import com.rodrigoguerrero.domain.common.DateConverter
import com.rodrigoguerrero.domain.common.ImageSize
import com.rodrigoguerrero.domain.common.ImageUrlBuilder
import com.rodrigoguerrero.domain.home.api.models.Genre
import com.rodrigoguerrero.domain.home.api.models.TrendingMovie
import javax.inject.Inject
import com.rodrigoguerrero.repository.movies.api.models.Trending as RepoTrendingMovie

internal class TrendingMovieMapper @Inject constructor(
    private val genresMapper: GenresMapper,
    private val imageUrlBuilder: ImageUrlBuilder,
    private val dateConverter: DateConverter,
) {
    fun toTrendingMovies(
        trendingMovies: List<RepoTrendingMovie>,
        genres: List<Genre>,
    ): List<TrendingMovie> {
        return trendingMovies.map { trendingMovie ->
            toTrendingMovie(
                trendingMovie = trendingMovie,
                genres = genres,
            )
        }
    }

    private fun toTrendingMovie(
        trendingMovie: RepoTrendingMovie,
        genres: List<Genre>,
    ): TrendingMovie = TrendingMovie(
        id = trendingMovie.id,
        title = trendingMovie.title,
        imageUrl = imageUrlBuilder.buildUrl(
            path = trendingMovie.imageUrl,
            imageSize = ImageSize.W300,
        ),
        genres = genresMapper.filterGenresByIds(
            genreIds = trendingMovie.genreIds,
            genres = genres,
        ),
        popularity = trendingMovie.popularity,
        releaseDate = dateConverter.toEpochMilliseconds(
            date = trendingMovie.releaseDate,
        ) ?: Long.MAX_VALUE
    )
}
