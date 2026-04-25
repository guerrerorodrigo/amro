package com.rodrigoguerrero.domain.details.impl.mappers

import com.rodrigoguerrero.domain.common.CurrencyFormatter
import com.rodrigoguerrero.domain.common.DateConverter
import com.rodrigoguerrero.domain.common.ImageSize
import com.rodrigoguerrero.domain.common.ImageUrlBuilder
import com.rodrigoguerrero.domain.details.api.models.MovieDetails
import com.rodrigoguerrero.repository.movies.api.models.MovieDetails as RepoMovieDetails
import javax.inject.Inject

private const val IMDB_BASE_URL = "https://www.imdb.com/title/"

internal class MovieDetailsMapper @Inject constructor(
    private val imageUrlBuilder: ImageUrlBuilder,
    private val currencyFormatter: CurrencyFormatter,
    private val dateConverter: DateConverter,
) {
    fun toMovieDetails(details: RepoMovieDetails): MovieDetails = MovieDetails(
        title = details.title,
        tagline = details.tagline,
        overview = details.overview,
        budget = currencyFormatter.format(details.budget),
        imdbUrl = "$IMDB_BASE_URL${details.imdbId}",
        posterUrl = imageUrlBuilder.buildUrl(
            path = details.posterPath,
            imageSize = ImageSize.Original,
        ),
        voteAverage = details.voteAverage,
        revenue = currencyFormatter.format(details.revenue),
        runtime = details.runtime,
        genres = details.genres.map { it.name },
        voteCount = details.voteCount,
        status = details.status,
        releaseDate = dateConverter.toReadableDate(details.releaseDate),
    )
}
