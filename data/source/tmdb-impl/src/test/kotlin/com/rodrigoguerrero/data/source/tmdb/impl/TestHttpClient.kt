package com.rodrigoguerrero.data.source.tmdb.impl

import com.rodrigoguerrero.data.source.tmdb.impl.data.errorResponse
import com.rodrigoguerrero.data.source.tmdb.impl.data.genresResponse
import com.rodrigoguerrero.data.source.tmdb.impl.data.movieDetailsResponse
import com.rodrigoguerrero.data.source.tmdb.impl.data.trendingResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal object TestHttpClient {
    private val headers = headersOf(
        HttpHeaders.ContentType,
        ContentType.Application.Json.toString()
    )

    fun buildSuccessHttpClient() = buildTestHttpClient(
        isError = false,
        httpStatusCode = HttpStatusCode.OK,
    )

    fun buildHttpClient(engine: HttpClientEngine) = HttpClient(engine) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        TmdbRemoteExceptionHandler.exceptionHandling(this)
    }

    fun buildErrorHttpClient(httpStatusCode: HttpStatusCode) = buildTestHttpClient(
        isError = true,
        httpStatusCode = httpStatusCode,
    )

    private fun buildTestHttpClient(isError: Boolean, httpStatusCode: HttpStatusCode): HttpClient {
        val mockEngine = if (isError) {
            getErrorMockEngine(status = httpStatusCode)
        } else {
            getMockEngine()
        }

        return buildHttpClient(engine = mockEngine)
    }

    private fun getMockEngine() = MockEngine { request ->
        when (request.url.encodedPath) {
            "/trending/movie/week" -> respond(
                content = trendingResponse,
                status = HttpStatusCode.OK,
                headers = headers,
            )

            "/genre/movie/list" -> respond(
                content = genresResponse,
                status = HttpStatusCode.OK,
                headers = headers,
            )

            "/movie/123" -> respond(
                content = movieDetailsResponse,
                status = HttpStatusCode.OK,
                headers = headers,
            )

            else -> respond(
                content = errorResponse,
                status = HttpStatusCode.NotFound,
                headers = headers,
            )
        }
    }

    private fun getErrorMockEngine(
        status: HttpStatusCode,
    ) = MockEngine {
        respond(
            content = errorResponse,
            status = status,
            headers = headers,
        )
    }
}
