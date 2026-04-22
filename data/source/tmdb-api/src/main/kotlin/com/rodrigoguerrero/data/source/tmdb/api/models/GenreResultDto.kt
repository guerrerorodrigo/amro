package com.rodrigoguerrero.data.source.tmdb.api.models

import kotlinx.serialization.Serializable

/**
 * DTO containing the [List] of [GenreDto]s
 * @param genres the [List] of [GenreDto]s
 */
@Serializable
data class GenreResultDto(
    val genres: List<GenreDto>,
)
