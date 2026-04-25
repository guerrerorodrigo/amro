package com.rodrigoguerrero.domain.home.impl

import com.rodrigoguerrero.domain.home.api.GetHomeContentInteractor
import com.rodrigoguerrero.domain.home.api.models.HomeContent
import com.rodrigoguerrero.domain.home.impl.mappers.GenresMapper
import com.rodrigoguerrero.domain.home.impl.mappers.TrendingMovieMapper
import com.rodrigoguerrero.repository.movies.api.MovieGenresRepository
import com.rodrigoguerrero.repository.movies.api.MoviesRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

internal class GetHomeContentInteractorImpl @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val genresRepository: MovieGenresRepository,
    private val trendingMovieMapper: TrendingMovieMapper,
    private val genresMapper: GenresMapper,
) : GetHomeContentInteractor {
    override suspend fun getTrendingMovies(
        totalPages: Int,
    ): Result<HomeContent> = runCatching {
        val trendingMovies = coroutineScope {
            List(totalPages) {
                async { moviesRepository.getTrendingMovies(page = it + 1) }
            }.awaitAll()
        }
        val successResults = trendingMovies
            .filter { it.isSuccess }
            .mapNotNull { it.getOrNull() }
            .flatten()
            .distinctBy { it.id }

        if (successResults.isEmpty()) {
            throw IllegalStateException("All operations failed")
        }

        val repoGenres = genresRepository.getMovieGenres().getOrNull().orEmpty()
        val genres = genresMapper.toGenres(genres = repoGenres)
        HomeContent(
            trendingMovies = trendingMovieMapper.toTrendingMovies(
                trendingMovies = successResults,
                genres = genres,
            ),
            genres = genres,
        )
    }
}
