package com.rodrigoguerrero.data.source.tmdb.impl

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val TMDB_API_BASE_URL = "api.themoviedb.org/3/"

internal object HttpClientProvider {
    fun createHttpClient(
        httpClientEngine: HttpClientEngine,
        apiKey: String,
    ) = HttpClient(httpClientEngine) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }

        TmdbRemoteExceptionHandler.exceptionHandling(this)

        defaultRequest {
            url {
                host = TMDB_API_BASE_URL
                protocol = URLProtocol.HTTPS
                headers.append("Authorization", "Bearer $apiKey")
            }
        }
    }
}
