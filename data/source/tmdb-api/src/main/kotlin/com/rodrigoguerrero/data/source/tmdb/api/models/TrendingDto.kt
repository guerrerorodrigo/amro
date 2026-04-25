package com.rodrigoguerrero.data.source.tmdb.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * DTO representing a trending media
 * @param id the ID of the trending media
 * @param title the title of the trending media
 * @param imageUrl the image URL of the trending media
 * @param genreIds [List] of IDs of the genres of the trending media
 * @param popularity the popularity of the trending media
 * @param releaseDate the release date of the trending media
 */
@Serializable
data class TrendingDto(
    val id: Long,
    val title: String,
    @SerialName("poster_path")
    val imageUrl: String,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    val popularity: Double,
    @SerialName("release_date")
    val releaseDate: String,
)
