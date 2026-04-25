package com.rodrigoguerrero.data.source.tmdb.impl

import com.rodrigoguerrero.data.source.tmdb.api.models.MediaType
import com.rodrigoguerrero.data.source.tmdb.api.models.TimeWindow
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@OptIn(ExperimentalCoroutinesApi::class)
internal class TmdbMediaDataSourceImplTest {
    private val unconfinedTestDispatcher = UnconfinedTestDispatcher()

    @Test
    @DisplayName(
        """
            Given mock server returns success for trending
            When calling getTrending with provided time window and media type
            Then result is success, and data is parsed correctly
        """
    )
    fun trendingSuccessTest() {
        val subject = TmdbMediaDataSourceImpl(httpClient = TestHttpClient.buildSuccessHttpClient())

        runTest(unconfinedTestDispatcher) {
            val result = subject.getTrending(
                timeWindow = TimeWindow.Week,
                mediaType = MediaType.Movie,
                page = 1,
            )

            val firstResult = result.getOrNull()?.results?.firstOrNull()

            assertAll(
                { assertTrue(result.isSuccess) },
                { assertEquals("Project Hail Mary", firstResult?.title) },
                { assertEquals(listOf(878, 12), firstResult?.genreIds) },
                { assertEquals("/yihdXomYb5kTeSivtFndMy5iDmf.jpg", firstResult?.imageUrl) },
            )
        }
    }

    @Test
    @DisplayName(
        """
            Given mock server returns failure
            When calling getTrending with provided time window and media type
            Then result is failure
        """
    )
    fun trendingFailureTest() {
        val subject = TmdbMediaDataSourceImpl(
            httpClient = TestHttpClient.buildErrorHttpClient(httpStatusCode = HttpStatusCode.BadRequest),
        )

        runTest(unconfinedTestDispatcher) {
            val result = subject.getTrending(
                timeWindow = TimeWindow.Week,
                mediaType = MediaType.Movie,
                page = 1,
            )

            assertTrue(result.isFailure)
        }
    }

    @Test
    @DisplayName(
        """
            Given mock server returns success for genres
            When calling getGenres with provided media type
            Then result is success, and data is parsed correctly
        """
    )
    fun genresSuccessTest() {
        val subject = TmdbMediaDataSourceImpl(httpClient = TestHttpClient.buildSuccessHttpClient())

        runTest(unconfinedTestDispatcher) {
            val result = subject.getGenres(
                mediaType = MediaType.Movie,
            )

            val firstResult = result.getOrNull()?.genres?.firstOrNull()

            assertAll(
                { assertTrue(result.isSuccess) },
                { assertEquals("Action", firstResult?.name) },
                { assertEquals(28, firstResult?.id) },
            )
        }
    }

    @Test
    @DisplayName(
        """
            Given mock server returns failure
            When calling getGenres with provided media type
            Then result is failure
        """
    )
    fun genresFailureTest() {
        val subject = TmdbMediaDataSourceImpl(
            httpClient = TestHttpClient.buildErrorHttpClient(httpStatusCode = HttpStatusCode.BadRequest),
        )

        runTest(unconfinedTestDispatcher) {
            val result = subject.getGenres(
                mediaType = MediaType.Movie,
            )
            assertTrue(result.isFailure)
        }
    }

    @Test
    @DisplayName(
        """
            Given mock server returns success for movie details
            When calling getMovieDetails with provided ID
            Then result is success, and data is parsed correctly
        """
    )
    fun movieDetailsSuccessTest() {
        val subject = TmdbMediaDataSourceImpl(httpClient = TestHttpClient.buildSuccessHttpClient())

        runTest(unconfinedTestDispatcher) {
            val result = subject.getMovieDetails(id = "123")

            val movieDetails = result.getOrNull()

            assertAll(
                { assertTrue(result.isSuccess) },
                { assertEquals("Project Hail Mary", movieDetails?.title) },
                { assertEquals("Released", movieDetails?.status) },
                { assertEquals("2026-03-15", movieDetails?.releaseDate) },
                { assertEquals("tt12042730", movieDetails?.imdbId) },
                { assertEquals(1631, movieDetails?.voteCount) },
                { assertEquals(573150314, movieDetails?.revenue) },
                { assertEquals(157, movieDetails?.runtime) },
                { assertEquals(200000000, movieDetails?.budget) },
                { assertEquals(8.221, movieDetails?.voteAverage) },
                { assertEquals("/yihdXomYb5kTeSivtFndMy5iDmf.jpg", movieDetails?.posterPath) },
                { assertEquals("/8Tfys3mDZVp4tNoH2ktm06a0Tau.jpg", movieDetails?.backdropPath) },
                { assertEquals("Believe in the Hail Mary.", movieDetails?.tagline) },
                { assertEquals("Science Fiction", movieDetails?.genres?.firstOrNull()?.name) },
                { assertEquals(878, movieDetails?.genres?.firstOrNull()?.id) },
                { assertEquals("Science teacher Ryland Grace wakes up on a spaceship light years from home with no recollection of who he is or how he got there. As his memory returns, he begins to uncover his mission: solve the riddle of the mysterious substance causing the sun to die out. He must call on his scientific knowledge and unorthodox ideas to save everything on Earth from extinction… but an unexpected friendship means he may not have to do it alone.", movieDetails?.overview) },
            )
        }
    }

    @Test
    @DisplayName(
        """
            Given mock server returns failure
            When calling getMovieDetails
            Then result is failure
        """
    )
    fun movieDetailsFailureTest() {
        val subject = TmdbMediaDataSourceImpl(
            httpClient = TestHttpClient.buildErrorHttpClient(httpStatusCode = HttpStatusCode.BadRequest),
        )

        runTest(unconfinedTestDispatcher) {
            val result = subject.getMovieDetails(id = "123")
            assertTrue(result.isFailure)
        }
    }
}
