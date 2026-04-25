package com.rodrigoguerrero.repository.movies.api

import com.rodrigoguerrero.repository.movies.api.models.MovieDetails
import com.rodrigoguerrero.repository.movies.api.models.Trending

/**
 * Repository for movies operations
 */
interface MoviesRepository {

    /**
     * Gets the trending movies for the provided [page]
     * @param page the page number to retrieve
     * @return a [Result] with a [List] of [Trending] movies
     */
    suspend fun getTrendingMovies(page: Int): Result<List<Trending>>

    /**
     * Gets the details of a movie with the provided [id]
     * @param id the ID of the movie
     * @return a [Result] with the [MovieDetails]
     */
    suspend fun getMovieDetails(id: String): Result<MovieDetails>
}
