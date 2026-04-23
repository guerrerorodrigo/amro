package com.rodrigoguerrero.repository.movies.api

import com.rodrigoguerrero.repository.movies.api.models.MovieDetails
import com.rodrigoguerrero.repository.movies.api.models.Trending

interface MoviesRepository {
    suspend fun getTrendingMovies(): Result<List<Trending>>
    suspend fun getMovieDetails(id: String): Result<MovieDetails>
}
