package com.rodrigoguerrero.repository.movies.api.models

/**
 * Class that represents a trending media object
 * @param title the title of the trending media object
 * @param imageUrl the image URL of the trending media object
 * @param genreIds [List] of [Int]s that represent the IDs of the genres of the media object
 */
data class Trending(
    val title: String,
    val imageUrl: String,
    val genreIds: List<Int>,
)
