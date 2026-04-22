package com.rodrigoguerrero.data.source.tmdb.api.models

import kotlinx.serialization.Serializable

/**
 * DTO representing a genre
 * @param id ID value of the genre
 * @param name name of the genre
 */
@Serializable
data class GenreDto(
    val id: Int,
    val name: String,
)
