package com.rodrigoguerrero.repository.movies.impl

import com.rodrigoguerrero.data.source.tmdb.api.MediaDataSource
import com.rodrigoguerrero.data.source.tmdb.api.models.MediaType
import com.rodrigoguerrero.data.source.tmdb.api.models.TimeWindow
import com.rodrigoguerrero.repository.movies.api.MoviesRepository
import com.rodrigoguerrero.repository.movies.api.models.MovieDetails
import com.rodrigoguerrero.repository.movies.api.models.Trending
import com.rodrigoguerrero.repository.movies.impl.mappers.MovieDetailsMapper
import com.rodrigoguerrero.repository.movies.impl.mappers.TrendingMapper
import javax.inject.Inject

internal class MoviesRepositoryImpl @Inject constructor(
    private val dataSource: MediaDataSource,
    private val trendingMapper: TrendingMapper,
    private val movieDetailsMapper: MovieDetailsMapper,
) : MoviesRepository {

    override suspend fun getTrendingMovies(
        page: Int,
    ): Result<List<Trending>> = runCatching {
        dataSource.getTrending(
            timeWindow = TimeWindow.Week,
            mediaType = MediaType.Movie,
            page = page,
        ).fold(
            onSuccess = { trendingMapper.toTrending(dto = it) },
            onFailure = { throw it },
        )
    }

    override suspend fun getMovieDetails(id: String): Result<MovieDetails> = runCatching {
        dataSource.getMovieDetails(id = id).fold(
            onSuccess = { movieDetailsMapper.toMovieDetails(it) },
            onFailure = { throw it },
        )
    }
}
