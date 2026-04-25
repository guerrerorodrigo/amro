package com.rodrigoguerrero.data.source.tmdb.api

import com.rodrigoguerrero.data.source.tmdb.api.models.GenreResultDto
import com.rodrigoguerrero.data.source.tmdb.api.models.MediaType
import com.rodrigoguerrero.data.source.tmdb.api.models.MovieDetailsDto
import com.rodrigoguerrero.data.source.tmdb.api.models.PageResultDto
import com.rodrigoguerrero.data.source.tmdb.api.models.TimeWindow
import com.rodrigoguerrero.data.source.tmdb.api.models.TrendingDto

/**
 * Data source for TMDB movies
 */
interface MediaDataSource {

    /**
     * Gets the trending results for the provided [timeWindow] and [mediaType]
     * @param page the page number to request
     * @param timeWindow [TimeWindow] for the trending media
     * @param mediaType [MediaType] for the trending media
     * @return a [Result] with a [PageResultDto] containing a list of trending media
     */
    suspend fun getTrending(
        page: Int,
        timeWindow: TimeWindow,
        mediaType: MediaType
    ): Result<PageResultDto<TrendingDto>>

    /**
     * Gets the genres for the provided [mediaType]
     * @param mediaType the [MediaType] to get genres for
     * @return a [Result] with the list of genres in [GenreResultDto]
     */
    suspend fun getGenres(mediaType: MediaType): Result<GenreResultDto>

    /**
     * Gets the details of the movie with the provided ID
     * @param id the ID of the movie to get the details for
     * @return a [Result] with the movie details in a [MovieDetailsDto]
     */
    suspend fun getMovieDetails(id: String): Result<MovieDetailsDto>
}
