package com.rodrigoguerrero.repository.movies.impl.mappers

import com.rodrigoguerrero.data.source.tmdb.api.models.MovieDetailsDto
import com.rodrigoguerrero.repository.movies.api.models.MovieDetails
import javax.inject.Inject

internal class MovieDetailsMapper @Inject constructor(
    private val genreMapper: GenreMapper,
) {
    fun toMovieDetails(dto: MovieDetailsDto): MovieDetails = MovieDetails(
        title = dto.title,
        tagline = dto.tagline,
        overview = dto.overview,
        budget = dto.budget,
        imdbId = dto.imdbId,
        backdropPath = dto.backdropPath,
        posterPath = dto.posterPath,
        voteAverage = dto.voteAverage,
        revenue = dto.revenue,
        runtime = dto.runtime,
        genres = genreMapper.toGenres(genresDto = dto.genres),
        voteCount = dto.voteCount,
        status = dto.status,
        releaseDate = dto.releaseDate,
    )
}
