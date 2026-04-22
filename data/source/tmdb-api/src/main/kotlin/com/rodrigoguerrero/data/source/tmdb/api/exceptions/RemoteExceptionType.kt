package com.rodrigoguerrero.data.source.tmdb.api.exceptions

sealed interface RemoteExceptionType {
    data object InvalidService : RemoteExceptionType
    data object AuthenticationFailed : RemoteExceptionType
    data object InvalidFormat : RemoteExceptionType
    data object InvalidParameters : RemoteExceptionType
    data object InvalidId : RemoteExceptionType
    data object InvalidApiKey : RemoteExceptionType
    data object DuplicateEntry : RemoteExceptionType
    data object ServiceOffline : RemoteExceptionType
    data object SuspendedApiKey : RemoteExceptionType
    data object InternalError : RemoteExceptionType
    data object Failed : RemoteExceptionType
    data object ServiceDenied : RemoteExceptionType
    data object SessionDenied : RemoteExceptionType
    data object ValidationFailed : RemoteExceptionType
    data object InvalidAcceptHeader : RemoteExceptionType
    data object InvalidDateRange : RemoteExceptionType
    data object InvalidPage : RemoteExceptionType
    data object InvalidDate : RemoteExceptionType
    data object TimedOut : RemoteExceptionType
    data object RequestCountOverLimit : RemoteExceptionType
    data object TooManyAppend : RemoteExceptionType
    data object InvalidTimezone : RemoteExceptionType
    data object MustConfirm : RemoteExceptionType
    data object InvalidRequestToken : RemoteExceptionType
    data object InvalidToken : RemoteExceptionType
    data object ResourceNotFound : RemoteExceptionType
    data object SessionNotFound : RemoteExceptionType
    data object ResourceIsPrivate : RemoteExceptionType
    data object RequestMethodNotSupported : RemoteExceptionType
    data object CouldNotConnect : RemoteExceptionType
    data object Maintenance : RemoteExceptionType
    data object InvalidInput : RemoteExceptionType
    data object UnknownClientError : RemoteExceptionType
    data object UnknownServerError : RemoteExceptionType
    data object ParseError : RemoteExceptionType
    data object Unknown : RemoteExceptionType
}
