package com.rodrigoguerrero.domain.home.impl

import com.rodrigoguerrero.domain.home.api.models.TrendingMovie
import com.rodrigoguerrero.domain.home.impl.mappers.GenresMapper
import com.rodrigoguerrero.domain.home.impl.mappers.TrendingMovieMapper
import com.rodrigoguerrero.domain.home.impl.testdata.genres
import com.rodrigoguerrero.domain.home.impl.testdata.trending
import com.rodrigoguerrero.repository.movies.api.MovieGenresRepository
import com.rodrigoguerrero.repository.movies.api.MoviesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertInstanceOf
import com.rodrigoguerrero.repository.movies.api.models.Genre as RepoGenre

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetHomeContentInteractorImplTest {
    private val unconfinedTestDispatcher = UnconfinedTestDispatcher()
    private val moviesRepository = mockk<MoviesRepository>()
    private val genresRepository = mockk<MovieGenresRepository>()
    private val trendingMovieMapper = mockk<TrendingMovieMapper>()
    private val genresMapper = mockk<GenresMapper>()

    private val subject = GetHomeContentInteractorImpl(
        moviesRepository = moviesRepository,
        genresRepository = genresRepository,
        trendingMovieMapper = trendingMovieMapper,
        genresMapper = genresMapper,
    )

    @Test
    @DisplayName(
        """
            Given repositories return success and mapper returns mapped list of trending movies
            When calling getTrendingMovies()
            Then result is success, repositories are called, and mapper is called with correct data
        """
    )
    fun getTrendingMoviesSuccessTest() {
        val genresRepo = listOf<RepoGenre>(mockk())
        val trendingDomain = listOf<TrendingMovie>(mockk())

        coEvery { genresRepository.getMovieGenres() } returns Result.success(genresRepo)
        coEvery { moviesRepository.getTrendingMovies(any()) } returns Result.success(listOf(trending))
        every { trendingMovieMapper.toTrendingMovies(any(), any()) } returns trendingDomain
        every { genresMapper.toGenres(any()) } returns genres

        runTest(unconfinedTestDispatcher) {
            val result = subject.getTrendingMovies()

            assertAll(
                { assertTrue(result.isSuccess) },
                { assertEquals(trendingDomain, result.getOrNull()?.trendingMovies) },
                { assertEquals(genres, result.getOrNull()?.genres) },
                { coVerify(exactly = 1) { genresRepository.getMovieGenres() } },
                { coVerify(exactly = 5) { moviesRepository.getTrendingMovies(any()) } },
                { coVerify(exactly = 1) { genresMapper.toGenres(genresRepo) } },
                {
                    verify(exactly = 1) {
                        trendingMovieMapper.toTrendingMovies(
                            trendingMovies = listOf(trending),
                            genres = genres,
                        )
                    }
                },
            )
        }
    }

    @Test
    @DisplayName(
        """
            Given trending movies repo returns failure
            When calling getTrendingMovies()
            Then result is failure, genres and movies repos are called, and mapper is not called
        """
    )
    fun getTrendingMoviesRepoFailureTest() {
        coEvery { genresRepository.getMovieGenres() } returns Result.success(mockk())
        coEvery { moviesRepository.getTrendingMovies(any()) } returns Result.failure(Exception())

        runTest(unconfinedTestDispatcher) {
            val result = subject.getTrendingMovies()

            assertAll(
                { assertTrue(result.isFailure) },
                { assertInstanceOf<IllegalStateException>(result.exceptionOrNull()) },
                { coVerify(exactly = 5) { moviesRepository.getTrendingMovies(any()) } },
                { coVerify(exactly = 0) { genresRepository.getMovieGenres() } },
                { verify(exactly = 0) { trendingMovieMapper.toTrendingMovies(any(), any()) } },
            )
        }
    }

    @Test
    @DisplayName(
        """
            Given trending movies mapper throws exception
            When calling getTrendingMovies()
            Then result is failure
        """
    )
    fun getTrendingMoviesMapperFailureTest() {
        coEvery { genresRepository.getMovieGenres() } returns Result.success(listOf(mockk()))
        coEvery { moviesRepository.getTrendingMovies(any()) } returns Result.success(listOf(trending))
        every { genresMapper.toGenres(any()) } returns genres
        every { trendingMovieMapper.toTrendingMovies(any(), any()) } throws Exception()

        runTest(unconfinedTestDispatcher) {
            val result = subject.getTrendingMovies(totalPages = 1)

            assertAll(
                { assertTrue(result.isFailure) },
                { coVerify(exactly = 1) { genresRepository.getMovieGenres() } },
                { coVerify(exactly = 1) { moviesRepository.getTrendingMovies(any()) } },
                { verify(exactly = 1) { trendingMovieMapper.toTrendingMovies(any(), any()) } },
            )
        }
    }
}
