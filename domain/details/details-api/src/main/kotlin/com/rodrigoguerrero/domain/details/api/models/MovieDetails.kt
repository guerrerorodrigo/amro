package com.rodrigoguerrero.domain.details.api.models

/**
 * Class representing the movie details
 * @param title title of the movie
 * @param tagline tagline of the movie
 * @param overview an overview of what the movie is about
 * @param budget the budget for the movie
 * @param imdbUrl the URL for IMDB for the movie
 * @param posterUrl the URL for the movie poster
 * @param voteAverage the average of the votes for the movie
 * @param revenue the revenue made by the movie
 * @param runtime the runtime of the movie in minutes
 * @param genres [List] of genres for the movie
 * @param voteCount the total number of votes for the movie
 * @param status the status of the movie (released, in production, etc)
 * @param releaseDate the release date of the movie
 */
data class MovieDetails(
    val title: String,
    val tagline: String,
    val overview: String,
    val budget: String,
    val imdbUrl: String,
    val posterUrl: String,
    val voteAverage: Double,
    val revenue: String,
    val runtime: Int,
    val genres: List<String>,
    val voteCount: Int,
    val status: String,
    val releaseDate: String?,
)
