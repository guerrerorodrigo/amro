package com.rodrigoguerrero.repository.movies.impl

import com.rodrigoguerrero.data.source.tmdb.api.MediaDataSource
import com.rodrigoguerrero.data.source.tmdb.api.models.MediaType
import com.rodrigoguerrero.data.source.tmdb.api.models.TimeWindow
import com.rodrigoguerrero.repository.movies.impl.mappers.MovieDetailsMapper
import com.rodrigoguerrero.repository.movies.impl.mappers.TrendingMapper
import com.rodrigoguerrero.repository.movies.impl.testdata.expectedMovieDetails
import com.rodrigoguerrero.repository.movies.impl.testdata.expectedTrending
import com.rodrigoguerrero.repository.movies.impl.testdata.movieDetailsDto
import com.rodrigoguerrero.repository.movies.impl.testdata.trendingPageResultDto
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

@OptIn(ExperimentalCoroutinesApi::class)
internal class MoviesRepositoryImplTest {
    private val unconfinedTestDispatcher = UnconfinedTestDispatcher()
    private val dataSource = mockk<MediaDataSource>()
    private val trendingMapper = mockk<TrendingMapper>()
    private val movieDetailsMapper = mockk<MovieDetailsMapper>()

    private val subject = MoviesRepositoryImpl(
        dataSource = dataSource,
        trendingMapper = trendingMapper,
        movieDetailsMapper = movieDetailsMapper,
    )

    @Test
    @DisplayName(
        """
            Given mapper returns mapped objects and data source returns a success with valid result
            When calling getTrendingMovies
            Then result is success, data returned is the mapped trending movies and trending mapper is called
        """
    )
    fun getTrendingMoviesSuccessTest() {
        every { trendingMapper.toTrending(any()) } returns listOf(expectedTrending)
        coEvery {
            dataSource.getTrending(any(), any())
        } returns Result.success(trendingPageResultDto)

        runTest(unconfinedTestDispatcher) {
            val result = subject.getTrendingMovies()

            assertAll(
                { assertTrue(result.isSuccess) },
                { assertEquals(listOf(expectedTrending), result.getOrNull()) },
                { verify(exactly = 1) { trendingMapper.toTrending(trendingPageResultDto) } },
                { coVerify(exactly = 1) { dataSource.getTrending(TimeWindow.Week, MediaType.Movie) } },
            )
        }
    }

    @Test
    @DisplayName(
        """
            Given data source for trending fails
            When calling getTrendingMovies
            Then result is failure and exception from data source is returned
        """
    )
    fun getTrendingDataSourceFailsTest() {
        val exception = Exception()
        coEvery {
            dataSource.getTrending(any(), any())
        } returns Result.failure(exception)

        runTest(unconfinedTestDispatcher) {
            val result = subject.getTrendingMovies()

            assertAll(
                { assertTrue(result.isFailure) },
                { assertEquals(exception, result.exceptionOrNull()) },
            )
        }
    }

    @Test
    @DisplayName(
        """
            Given data source succeeds and trending mapper throws an exception
            When calling getTrendingMovies
            Then result is failure, exception thrown is returned and mapper and datasource are called
        """
    )
    fun getTrendingMapperFailsTest() {
        val exception = Exception()
        coEvery {
            dataSource.getTrending(any(), any())
        } returns Result.success(trendingPageResultDto)
        every { trendingMapper.toTrending(any()) } throws exception

        runTest(unconfinedTestDispatcher) {
            val result = subject.getTrendingMovies()

            assertAll(
                { assertTrue(result.isFailure) },
                { assertEquals(exception, result.exceptionOrNull()) },
                { verify(exactly = 1) { trendingMapper.toTrending(trendingPageResultDto) } },
                { coVerify(exactly = 1) { dataSource.getTrending(TimeWindow.Week, MediaType.Movie) } },
            )
        }
    }

    @Test
    @DisplayName(
        """
            Given mapper returns mapped objects and data source returns a success with valid result
            When calling getMovieDetails
            Then result is success, data returned is the mapped movie details and mapper is called
        """
    )
    fun getMovieDetailsSuccessTest() {
        every { movieDetailsMapper.toMovieDetails(any()) } returns expectedMovieDetails
        coEvery {
            dataSource.getMovieDetails(any())
        } returns Result.success(movieDetailsDto)

        runTest(unconfinedTestDispatcher) {
            val result = subject.getMovieDetails(id = "123")

            assertAll(
                { assertTrue(result.isSuccess) },
                { assertEquals(expectedMovieDetails, result.getOrNull()) },
                { verify(exactly = 1) { movieDetailsMapper.toMovieDetails(movieDetailsDto) } },
                { coVerify(exactly = 1) { dataSource.getMovieDetails(id = "123") } },
            )
        }
    }

    @Test
    @DisplayName(
        """
            Given data source for movie details fails
            When calling getMovieDetails
            Then result is failure and exception from data source is returned
        """
    )
    fun getMovieDetailsDataSourceFailsTest() {
        val exception = Exception()
        coEvery {
            dataSource.getMovieDetails(any())
        } returns Result.failure(exception)

        runTest(unconfinedTestDispatcher) {
            val result = subject.getMovieDetails(id = "123")

            assertAll(
                { assertTrue(result.isFailure) },
                { assertEquals(exception, result.exceptionOrNull()) },
            )
        }
    }

    @Test
    @DisplayName(
        """
            Given data source succeeds and trending mapper throws an exception
            When calling getTrendingMovies
            Then result is failure, exception thrown is returned and mapper and datasource are called
        """
    )
    fun getMovieDetailsMapperFailsTest() {
        val exception = Exception()
        coEvery {
            dataSource.getMovieDetails(any())
        } returns Result.success(movieDetailsDto)
        every { movieDetailsMapper.toMovieDetails(any()) } throws exception

        runTest(unconfinedTestDispatcher) {
            val result = subject.getMovieDetails(id = "123")

            assertAll(
                { assertTrue(result.isFailure) },
                { assertEquals(exception, result.exceptionOrNull()) },
                { verify(exactly = 1) { movieDetailsMapper.toMovieDetails(movieDetailsDto) } },
                { coVerify(exactly = 1) { dataSource.getMovieDetails(id = "123") } },
            )
        }
    }
}
