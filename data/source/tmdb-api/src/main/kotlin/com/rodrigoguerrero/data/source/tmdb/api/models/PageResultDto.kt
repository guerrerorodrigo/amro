package com.rodrigoguerrero.data.source.tmdb.api.models

import kotlinx.serialization.Serializable

/**
 * Object that encapsulates a page with results
 * @param results [List] of results of type [T]
 */
@Serializable
data class PageResultDto<T>(
    val results: List<T>,
)
