package com.rodrigoguerrero.domain.home.api.models

/**
 * Class that represents a domain trending movie
 * @param id the ID of the movie
 * @param title the title of the movie
 * @param imageUrl the URL of the movie image
 * @param genres a [List] of [Genre]s for the movie
 * @param popularity the popularity of the movie
 * @param releaseDate release date of the movie in milliseconds
 */
data class TrendingMovie(
    val id: Long,
    val title: String,
    val imageUrl: String,
    val genres: List<Genre>,
    val popularity: Double,
    val releaseDate: Long,
)
