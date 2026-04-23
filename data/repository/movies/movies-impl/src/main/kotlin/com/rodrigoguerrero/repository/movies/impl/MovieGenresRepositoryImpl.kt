package com.rodrigoguerrero.repository.movies.impl

import com.rodrigoguerrero.data.source.tmdb.api.MediaDataSource
import com.rodrigoguerrero.data.source.tmdb.api.models.MediaType
import com.rodrigoguerrero.repository.movies.api.MovieGenresRepository
import com.rodrigoguerrero.repository.movies.api.models.Genre
import com.rodrigoguerrero.repository.movies.impl.mappers.GenreMapper
import javax.inject.Inject

internal class MovieGenresRepositoryImpl @Inject constructor(
    private val dataSource: MediaDataSource,
    private val genreMapper: GenreMapper,
) : MovieGenresRepository {
    private var genres: List<Genre> = emptyList()

    override suspend fun getMovieGenres(): Result<List<Genre>> = if (genres.isNotEmpty()) {
        Result.success(genres)
    } else {
        val result = fetchMovieGenres()
        genres = result.getOrNull().orEmpty()
        result
    }

    private suspend fun fetchMovieGenres(): Result<List<Genre>> = runCatching {
        dataSource.getGenres(mediaType = MediaType.Movie).fold(
            onSuccess = { genreMapper.toGenres(it.genres) },
            onFailure = { throw it },
        )
    }
}
