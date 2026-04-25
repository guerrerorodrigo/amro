package com.rodrigoguerrero.domain.details.impl

import com.rodrigoguerrero.domain.details.api.GetMovieDetailsInteractor
import com.rodrigoguerrero.domain.details.api.models.MovieDetails
import com.rodrigoguerrero.domain.details.impl.mappers.MovieDetailsMapper
import com.rodrigoguerrero.repository.movies.api.MoviesRepository
import javax.inject.Inject

internal class GetMovieDetailsInteractorImpl @Inject constructor(
    private val repository: MoviesRepository,
    private val mapper: MovieDetailsMapper,
) : GetMovieDetailsInteractor {
    override suspend fun getMovieDetails(id: String): Result<MovieDetails> = runCatching {
        repository
            .getMovieDetails(id = id)
            .fold(
                onSuccess = { mapper.toMovieDetails(details = it) },
                onFailure = { throw it },
            )
    }
}
