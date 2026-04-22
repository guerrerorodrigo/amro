package com.rodrigoguerrero.data.source.tmdb.impl

import com.rodrigoguerrero.data.source.tmdb.api.MediaDataSource
import com.rodrigoguerrero.data.source.tmdb.api.models.GenreResultDto
import com.rodrigoguerrero.data.source.tmdb.api.models.MediaType
import com.rodrigoguerrero.data.source.tmdb.api.models.MovieDetailsDto
import com.rodrigoguerrero.data.source.tmdb.api.models.PageResultDto
import com.rodrigoguerrero.data.source.tmdb.api.models.TimeWindow
import com.rodrigoguerrero.data.source.tmdb.api.models.TrendingDto
import com.rodrigoguerrero.data.source.tmdb.impl.di.TmdbClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

internal class TmdbMediaDataSourceImpl @Inject constructor(
    @TmdbClient private val httpClient: HttpClient,
) : MediaDataSource {
    override suspend fun getTrending(
        timeWindow: TimeWindow,
        mediaType: MediaType,
    ): Result<PageResultDto<TrendingDto>> = runCatching {
        httpClient
            .get("trending/${mediaType.value}/${timeWindow.value}")
            .body()
    }

    override suspend fun getGenres(mediaType: MediaType): Result<GenreResultDto> = runCatching {
        httpClient
            .get("genre/${mediaType.value}/list")
            .body()
    }

    override suspend fun getMovieDetails(id: String): Result<MovieDetailsDto> = runCatching {
        httpClient
            .get("movie/$id")
            .body()
    }
}
