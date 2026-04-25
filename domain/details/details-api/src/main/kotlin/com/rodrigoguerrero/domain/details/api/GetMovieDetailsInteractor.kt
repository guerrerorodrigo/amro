package com.rodrigoguerrero.domain.details.api

import com.rodrigoguerrero.domain.details.api.models.MovieDetails

/**
 * Interactor to fetch movie details
 */
interface GetMovieDetailsInteractor {

    /**
     * Gets the movie details for the movie with the provided [id]
     * @param id the ID of the movie to fetch the details for
     * @return a [Result] with the [MovieDetails] data
     */
    suspend fun getMovieDetails(id: String): Result<MovieDetails>
}
