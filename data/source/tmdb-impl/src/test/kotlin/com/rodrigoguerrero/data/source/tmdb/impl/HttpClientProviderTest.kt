package com.rodrigoguerrero.data.source.tmdb.impl

import com.rodrigoguerrero.data.source.tmdb.impl.data.movieDetailsResponse
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.headersOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@OptIn(ExperimentalCoroutinesApi::class)
internal class HttpClientProviderTest {
    private val unconfinedTestDispatcher = UnconfinedTestDispatcher()

    @Test
    @DisplayName(
        """
            Given a mock engine that responds success
            When creating the HttpClient and performing a get request
            Then the request data is correct
        """
    )
    fun testCreateHttpClient() {
        var httpRequestData: HttpRequestData? = null
        val mockEngine = MockEngine { request ->
            httpRequestData = request
            respond(
                content = movieDetailsResponse,
                status = HttpStatusCode.OK,
                headers = headersOf(
                    HttpHeaders.ContentType,
                    ContentType.Application.Json.toString()
                ),
            )
        }

        val client = HttpClientProvider.createHttpClient(mockEngine, "testApiKey")

        runTest(unconfinedTestDispatcher) {
            val result = client.get("/movie/details/123")
            assertAll(
                { assertEquals("api.themoviedb.org/3/", httpRequestData?.url?.host) },
                { assertEquals(URLProtocol.HTTPS, httpRequestData?.url?.protocol) },
                { assertEquals("Bearer testApiKey", httpRequestData?.headers["Authorization"]) },
                { assertEquals(HttpStatusCode.OK, result.status)},
            )
        }
    }
}
