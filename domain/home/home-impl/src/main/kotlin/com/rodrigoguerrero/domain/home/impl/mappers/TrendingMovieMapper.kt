package com.rodrigoguerrero.domain.home.impl.mappers

import com.rodrigoguerrero.domain.common.ImageSize
import com.rodrigoguerrero.domain.common.ImageUrlBuilder
import com.rodrigoguerrero.domain.home.api.models.Genre
import com.rodrigoguerrero.domain.home.api.models.TrendingMovie
import javax.inject.Inject
import com.rodrigoguerrero.repository.movies.api.models.Genre as RepoGenre
import com.rodrigoguerrero.repository.movies.api.models.Trending as RepoTrendingMovie

internal class TrendingMovieMapper @Inject constructor(
    private val genresMapper: GenresMapper,
    private val imageUrlBuilder: ImageUrlBuilder,
) {
    fun toTrendingMovies(
        trendingMovies: List<RepoTrendingMovie>,
        genres: List<RepoGenre>,
    ): List<TrendingMovie> {
        val genres = genresMapper.toGenres(genres = genres)
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
        title = trendingMovie.title,
        imageUrl = imageUrlBuilder.buildUrl(
            path = trendingMovie.imageUrl,
            imageSize = ImageSize.W300,
        ),
        genres = genresMapper.filterGenresByIds(
            genreIds = trendingMovie.genreIds,
            genres = genres,
        ),
    )
}
