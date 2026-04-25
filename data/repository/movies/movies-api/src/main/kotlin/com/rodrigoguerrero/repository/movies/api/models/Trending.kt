package com.rodrigoguerrero.repository.movies.api.models

/**
 * Class that represents a trending media object
 * @param id the ID of the trending media
 * @param title the title of the trending media object
 * @param imageUrl the image URL of the trending media object
 * @param genreIds [List] of [Int]s that represent the IDs of the genres of the media object
 * @param popularity the popularity of the trending media
 * @param releaseDate the release date of the trending media
 */
data class Trending(
    val id: Long,
    val title: String,
    val imageUrl: String,
    val genreIds: List<Int>,
    val popularity: Double,
    val releaseDate: String,
)
