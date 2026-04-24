package com.rodrigoguerrero.domain.home.impl

import com.rodrigoguerrero.domain.home.api.GetHomeContentInteractor
import com.rodrigoguerrero.domain.home.api.models.HomeContent
import com.rodrigoguerrero.domain.home.impl.mappers.GenresMapper
import com.rodrigoguerrero.domain.home.impl.mappers.TrendingMovieMapper
import com.rodrigoguerrero.repository.movies.api.MovieGenresRepository
import com.rodrigoguerrero.repository.movies.api.MoviesRepository
import javax.inject.Inject

internal class GetHomeContentInteractorImpl @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val genresRepository: MovieGenresRepository,
    private val trendingMovieMapper: TrendingMovieMapper,
    private val genresMapper: GenresMapper,
) : GetHomeContentInteractor {
    override suspend fun getTrendingMovies(): Result<HomeContent> = runCatching {
        val repoGenres = genresRepository.getMovieGenres().getOrNull().orEmpty()
        moviesRepository.getTrendingMovies().fold(
            onSuccess = {
                val genres = genresMapper.toGenres(genres = repoGenres)
                HomeContent(
                    trendingMovies = trendingMovieMapper.toTrendingMovies(
                        trendingMovies = it,
                        genres = genres,
                    ),
                    genres = genres,
                )
            },
            onFailure = { throw it },
        )
    }
}
