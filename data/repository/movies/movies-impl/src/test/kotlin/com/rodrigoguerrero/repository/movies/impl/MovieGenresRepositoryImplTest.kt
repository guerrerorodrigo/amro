package com.rodrigoguerrero.repository.movies.impl

import com.rodrigoguerrero.data.source.tmdb.api.MediaDataSource
import com.rodrigoguerrero.data.source.tmdb.api.models.MediaType
import com.rodrigoguerrero.repository.movies.impl.mappers.GenreMapper
import com.rodrigoguerrero.repository.movies.impl.testdata.anotherExpectedGenre
import com.rodrigoguerrero.repository.movies.impl.testdata.expectedGenre
import com.rodrigoguerrero.repository.movies.impl.testdata.genresResultDto
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
internal class MovieGenresRepositoryImplTest {
    private val unconfinedTestDispatcher = UnconfinedTestDispatcher()
    private val dataSource = mockk<MediaDataSource>()
    private val genreMapper = mockk<GenreMapper>()

    private val subject = MovieGenresRepositoryImpl(
        dataSource = dataSource,
        genreMapper = genreMapper,
    )

    @Test
    @DisplayName(
        """
            Given datasource returns success, mapper returns correctly and it is the first call to get the genres
            When calling getMovieGenres
            Then result is success, result contains correct genres and datasource and mapper are called
            with correct values
        """
    )
    fun getMovieGenresFirstCallSuccess() {
        coEvery { dataSource.getGenres(any()) } returns Result.success(genresResultDto)
        every { genreMapper.toGenres(any()) } returns listOf(expectedGenre, anotherExpectedGenre)

        runTest(unconfinedTestDispatcher) {
            val result = subject.getMovieGenres()

            assertAll(
                { assertTrue(result.isSuccess) },
                { assertEquals(listOf(expectedGenre, anotherExpectedGenre), result.getOrNull()) },
                { coVerify(exactly = 1) { dataSource.getGenres(mediaType = MediaType.Movie) } },
                { verify(exactly = 1) { genreMapper.toGenres(genresResultDto.genres) } },
            )
        }
    }

    @Test
    @DisplayName(
        """
            Given datasource returns success, 
            When calling getMovieGenres multiple times
            Then result is success, and data source is only called once
        """
    )
    fun getMovieGenresMultipleCallSuccess() {
        coEvery { dataSource.getGenres(any()) } returns Result.success(genresResultDto)
        every { genreMapper.toGenres(any()) } returns listOf(expectedGenre, anotherExpectedGenre)

        runTest(unconfinedTestDispatcher) {
            subject.getMovieGenres()
            subject.getMovieGenres()
            val result = subject.getMovieGenres()

            assertAll(
                { assertTrue(result.isSuccess) },
                { coVerify(exactly = 1) { dataSource.getGenres(mediaType = MediaType.Movie) } },
                { verify(exactly = 1) { genreMapper.toGenres(genresResultDto.genres) } },
            )
        }
    }

    @Test
    @DisplayName(
        """
            Given datasource returns result
            When calling getMovieGenres
            Then result is failure, exception is returned and only datasource is called
        """
    )
    fun getGenresFailure() {
        val exception = Exception()
        coEvery { dataSource.getGenres(any()) } returns Result.failure(exception)

        runTest(unconfinedTestDispatcher) {
            val result = subject.getMovieGenres()

            assertAll(
                { assertTrue(result.isFailure) },
                { assertEquals(exception, result.exceptionOrNull()) },
                { coVerify(exactly = 1) { dataSource.getGenres(mediaType = MediaType.Movie) } },
                { verify(exactly = 0) { genreMapper.toGenres(genresResultDto.genres) } },
            )
        }
    }
}
