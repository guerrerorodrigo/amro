package com.rodrigoguerrero.repository.movies.api

import com.rodrigoguerrero.repository.movies.api.models.Genre

/**
 * Repository that returns the genres for movies
 */
interface MovieGenresRepository {
    /**
     * Gets the available genres for the movies
     * @return a [Result] with a [List] of [Genre]s
     */
    suspend fun getMovieGenres(): Result<List<Genre>>
}
