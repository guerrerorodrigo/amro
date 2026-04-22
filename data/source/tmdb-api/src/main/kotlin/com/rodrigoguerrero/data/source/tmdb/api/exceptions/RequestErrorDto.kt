package com.rodrigoguerrero.data.source.tmdb.api.exceptions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestErrorDto(
    val success: Boolean,
    @SerialName("status_code") val statusCode: Int? = null,
    @SerialName("status_message") val statusMessage: String? = null,
)
