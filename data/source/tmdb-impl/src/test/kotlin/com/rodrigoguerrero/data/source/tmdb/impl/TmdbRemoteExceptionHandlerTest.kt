package com.rodrigoguerrero.data.source.tmdb.impl

import com.rodrigoguerrero.data.source.tmdb.api.exceptions.RemoteException
import com.rodrigoguerrero.data.source.tmdb.api.exceptions.RemoteExceptionType
import com.rodrigoguerrero.data.source.tmdb.impl.data.authFailed
import com.rodrigoguerrero.data.source.tmdb.impl.data.authenticationFailed
import com.rodrigoguerrero.data.source.tmdb.impl.data.couldNotConnect
import com.rodrigoguerrero.data.source.tmdb.impl.data.duplicateEntry
import com.rodrigoguerrero.data.source.tmdb.impl.data.failed
import com.rodrigoguerrero.data.source.tmdb.impl.data.internalError
import com.rodrigoguerrero.data.source.tmdb.impl.data.invalidAcceptHeader
import com.rodrigoguerrero.data.source.tmdb.impl.data.invalidApiKey
import com.rodrigoguerrero.data.source.tmdb.impl.data.invalidDate
import com.rodrigoguerrero.data.source.tmdb.impl.data.invalidDateRange
import com.rodrigoguerrero.data.source.tmdb.impl.data.invalidFormat
import com.rodrigoguerrero.data.source.tmdb.impl.data.invalidId
import com.rodrigoguerrero.data.source.tmdb.impl.data.invalidIdClient
import com.rodrigoguerrero.data.source.tmdb.impl.data.invalidInput
import com.rodrigoguerrero.data.source.tmdb.impl.data.invalidPage
import com.rodrigoguerrero.data.source.tmdb.impl.data.invalidParams
import com.rodrigoguerrero.data.source.tmdb.impl.data.invalidRequestToken
import com.rodrigoguerrero.data.source.tmdb.impl.data.invalidService
import com.rodrigoguerrero.data.source.tmdb.impl.data.invalidTimezone
import com.rodrigoguerrero.data.source.tmdb.impl.data.invalidToken
import com.rodrigoguerrero.data.source.tmdb.impl.data.maintenance
import com.rodrigoguerrero.data.source.tmdb.impl.data.mustConfirm
import com.rodrigoguerrero.data.source.tmdb.impl.data.requestCountOverLimit
import com.rodrigoguerrero.data.source.tmdb.impl.data.requestMethodNotSupported
import com.rodrigoguerrero.data.source.tmdb.impl.data.resourceIsPrivate
import com.rodrigoguerrero.data.source.tmdb.impl.data.resourceNotFound
import com.rodrigoguerrero.data.source.tmdb.impl.data.serviceDenied
import com.rodrigoguerrero.data.source.tmdb.impl.data.serviceOffline
import com.rodrigoguerrero.data.source.tmdb.impl.data.sessionDenied
import com.rodrigoguerrero.data.source.tmdb.impl.data.sessionNotFound
import com.rodrigoguerrero.data.source.tmdb.impl.data.suspendedApiKey
import com.rodrigoguerrero.data.source.tmdb.impl.data.timedOut
import com.rodrigoguerrero.data.source.tmdb.impl.data.tooManyAppend
import com.rodrigoguerrero.data.source.tmdb.impl.data.unknownClient
import com.rodrigoguerrero.data.source.tmdb.impl.data.unknownServer
import com.rodrigoguerrero.data.source.tmdb.impl.data.validationFailed
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class TmdbRemoteExceptionHandlerTest {

    @ParameterizedTest(name = "response={0}, status={1}, expected={2}")
    @MethodSource("provideExceptionHandlingData")
    @DisplayName(
        """
            Given a response and a HttpStatusCode
            When processing an error response
            Then expected RemoteExceptionType is received
        """
    )
    fun testExceptionHandling(
        response: String,
        status: HttpStatusCode,
        expected: RemoteExceptionType,
    ) {
        val engine = MockEngine {
            respond(
                content = response,
                status = status,
                headers = headersOf(
                    HttpHeaders.ContentType,
                    ContentType.Application.Json.toString()
                ),
            )
        }

        runTest {
            val exception = assertThrows<RemoteException> {
                TestHttpClient.buildHttpClient(engine).get("http://localhost/test")
            }
            assertEquals(expected, exception.type)
        }
    }

    companion object {
        @JvmStatic
        fun provideExceptionHandlingData() = listOf(
            Arguments.arguments(invalidService, HttpStatusCode.ServiceUnavailable, RemoteExceptionType.InvalidService),
            Arguments.arguments(serviceOffline, HttpStatusCode.ServiceUnavailable, RemoteExceptionType.ServiceOffline),
            Arguments.arguments(internalError, HttpStatusCode.ServiceUnavailable, RemoteExceptionType.InternalError),
            Arguments.arguments(failed, HttpStatusCode.ServiceUnavailable, RemoteExceptionType.Failed),
            Arguments.arguments(timedOut, HttpStatusCode.ServiceUnavailable, RemoteExceptionType.TimedOut),
            Arguments.arguments(couldNotConnect, HttpStatusCode.ServiceUnavailable, RemoteExceptionType.CouldNotConnect),
            Arguments.arguments(invalidId, HttpStatusCode.ServiceUnavailable, RemoteExceptionType.InvalidId),
            Arguments.arguments(maintenance, HttpStatusCode.ServiceUnavailable, RemoteExceptionType.Maintenance),
            Arguments.arguments(unknownServer, HttpStatusCode.ServiceUnavailable, RemoteExceptionType.UnknownServerError),
            Arguments.arguments(authFailed, HttpStatusCode.BadRequest, RemoteExceptionType.AuthenticationFailed),
            Arguments.arguments(invalidFormat, HttpStatusCode.BadRequest, RemoteExceptionType.InvalidFormat),
            Arguments.arguments(invalidParams, HttpStatusCode.BadRequest, RemoteExceptionType.InvalidParameters),
            Arguments.arguments(invalidIdClient, HttpStatusCode.BadRequest, RemoteExceptionType.InvalidId),
            Arguments.arguments(invalidApiKey, HttpStatusCode.BadRequest, RemoteExceptionType.InvalidApiKey),
            Arguments.arguments(duplicateEntry, HttpStatusCode.BadRequest, RemoteExceptionType.DuplicateEntry),
            Arguments.arguments(suspendedApiKey, HttpStatusCode.BadRequest, RemoteExceptionType.SuspendedApiKey),
            Arguments.arguments(authenticationFailed, HttpStatusCode.BadRequest, RemoteExceptionType.AuthenticationFailed),
            Arguments.arguments(serviceDenied, HttpStatusCode.BadRequest, RemoteExceptionType.ServiceDenied),
            Arguments.arguments(sessionDenied, HttpStatusCode.BadRequest, RemoteExceptionType.SessionDenied),
            Arguments.arguments(validationFailed, HttpStatusCode.BadRequest, RemoteExceptionType.ValidationFailed),
            Arguments.arguments(invalidAcceptHeader, HttpStatusCode.BadRequest, RemoteExceptionType.InvalidAcceptHeader),
            Arguments.arguments(invalidDateRange, HttpStatusCode.BadRequest, RemoteExceptionType.InvalidDateRange),
            Arguments.arguments(invalidDate, HttpStatusCode.BadRequest, RemoteExceptionType.InvalidDate),
            Arguments.arguments(requestCountOverLimit, HttpStatusCode.BadRequest, RemoteExceptionType.RequestCountOverLimit),
            Arguments.arguments(tooManyAppend, HttpStatusCode.BadRequest, RemoteExceptionType.TooManyAppend),
            Arguments.arguments(invalidTimezone, HttpStatusCode.BadRequest, RemoteExceptionType.InvalidTimezone),
            Arguments.arguments(mustConfirm, HttpStatusCode.BadRequest, RemoteExceptionType.MustConfirm),
            Arguments.arguments(invalidRequestToken, HttpStatusCode.BadRequest, RemoteExceptionType.InvalidRequestToken),
            Arguments.arguments(resourceNotFound, HttpStatusCode.BadRequest, RemoteExceptionType.ResourceNotFound),
            Arguments.arguments(invalidToken, HttpStatusCode.BadRequest, RemoteExceptionType.InvalidToken),
            Arguments.arguments(sessionNotFound, HttpStatusCode.BadRequest, RemoteExceptionType.SessionNotFound),
            Arguments.arguments(resourceIsPrivate, HttpStatusCode.BadRequest, RemoteExceptionType.ResourceIsPrivate),
            Arguments.arguments(requestMethodNotSupported, HttpStatusCode.BadRequest, RemoteExceptionType.RequestMethodNotSupported),
            Arguments.arguments(invalidInput, HttpStatusCode.BadRequest, RemoteExceptionType.InvalidInput),
            Arguments.arguments(invalidPage, HttpStatusCode.BadRequest, RemoteExceptionType.InvalidPage),
            Arguments.arguments(unknownClient, HttpStatusCode.BadRequest, RemoteExceptionType.UnknownClientError),
            Arguments.arguments("invalid json", HttpStatusCode.BadRequest, RemoteExceptionType.ParseError),
        )
    }
}
