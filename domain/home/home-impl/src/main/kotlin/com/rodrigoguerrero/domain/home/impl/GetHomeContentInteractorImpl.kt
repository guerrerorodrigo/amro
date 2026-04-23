package com.rodrigoguerrero.domain.home.impl

import com.rodrigoguerrero.domain.home.api.GetHomeContentInteractor
import com.rodrigoguerrero.domain.home.api.models.TrendingMovie
import com.rodrigoguerrero.domain.home.impl.mappers.TrendingMovieMapper
import com.rodrigoguerrero.repository.movies.api.MovieGenresRepository
import com.rodrigoguerrero.repository.movies.api.MoviesRepository
import javax.inject.Inject

internal class GetHomeContentInteractorImpl @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val genresRepository: MovieGenresRepository,
    private val trendingMovieMapper: TrendingMovieMapper,
) : GetHomeContentInteractor {
    override suspend fun getTrendingMovies(): Result<List<TrendingMovie>> = runCatching {
        val genres = genresRepository.getMovieGenres().getOrNull().orEmpty()
        moviesRepository.getTrendingMovies().fold(
            onSuccess = {
                trendingMovieMapper.toTrendingMovies(trendingMovies = it, genres = genres)
            },
            onFailure = { throw it },
        )
    }
}
