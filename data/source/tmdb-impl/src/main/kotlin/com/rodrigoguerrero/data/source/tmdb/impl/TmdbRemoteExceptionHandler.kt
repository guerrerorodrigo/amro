package com.rodrigoguerrero.data.source.tmdb.impl

import com.rodrigoguerrero.data.source.tmdb.api.exceptions.RemoteException
import com.rodrigoguerrero.data.source.tmdb.api.exceptions.RemoteExceptionType
import com.rodrigoguerrero.data.source.tmdb.api.exceptions.RequestErrorDto
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ServerResponseException
import io.ktor.serialization.JsonConvertException

internal object TmdbRemoteExceptionHandler {
    fun <T : HttpClientEngineConfig> exceptionHandling(config: HttpClientConfig<T>) {
        with (config) {
            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, _ ->
                    val type = when (exception) {
                        is ClientRequestException -> processClientErrors(exception)
                        is ServerResponseException -> processServerErrors(exception)
                        is JsonConvertException -> RemoteExceptionType.ParseError
                        else -> RemoteExceptionType.Unknown
                    }
                    throw RemoteException(type)
                }
            }
        }
    }

    private suspend fun processClientErrors(exception: ClientRequestException): RemoteExceptionType {
        val exceptionResponse = exception.response
        val response = exceptionResponse.body<RequestErrorDto>()

        return when (response.statusCode) {
            3 -> RemoteExceptionType.AuthenticationFailed
            4 -> RemoteExceptionType.InvalidFormat
            5 -> RemoteExceptionType.InvalidParameters
            6 -> RemoteExceptionType.InvalidId
            7 -> RemoteExceptionType.InvalidApiKey
            8 -> RemoteExceptionType.DuplicateEntry
            10 -> RemoteExceptionType.SuspendedApiKey
            14 -> RemoteExceptionType.AuthenticationFailed
            16 -> RemoteExceptionType.ServiceDenied
            17 -> RemoteExceptionType.SessionDenied
            18 -> RemoteExceptionType.ValidationFailed
            19 -> RemoteExceptionType.InvalidAcceptHeader
            20 -> RemoteExceptionType.InvalidDateRange
            22 -> RemoteExceptionType.InvalidPage
            23 -> RemoteExceptionType.InvalidDate
            25 -> RemoteExceptionType.RequestCountOverLimit
            27 -> RemoteExceptionType.TooManyAppend
            28 -> RemoteExceptionType.InvalidTimezone
            29 -> RemoteExceptionType.MustConfirm
            33 -> RemoteExceptionType.InvalidRequestToken
            34 -> RemoteExceptionType.ResourceNotFound
            35 -> RemoteExceptionType.InvalidToken
            37 -> RemoteExceptionType.SessionNotFound
            39 -> RemoteExceptionType.ResourceIsPrivate
            42 -> RemoteExceptionType.RequestMethodNotSupported
            47 -> RemoteExceptionType.InvalidInput
            else -> RemoteExceptionType.UnknownClientError
        }
    }

    private suspend fun processServerErrors(exception: ServerResponseException): RemoteExceptionType {
        val exceptionResponse = exception.response
        val response = exceptionResponse.body<RequestErrorDto>()

        return when (response.statusCode) {
            2 -> RemoteExceptionType.InvalidService
            9 -> RemoteExceptionType.ServiceOffline
            11 -> RemoteExceptionType.InternalError
            15 -> RemoteExceptionType.Failed
            24 -> RemoteExceptionType.TimedOut
            43 -> RemoteExceptionType.CouldNotConnect
            44 -> RemoteExceptionType.InvalidId
            46 -> RemoteExceptionType.Maintenance
            else -> RemoteExceptionType.UnknownServerError
        }
    }
}
