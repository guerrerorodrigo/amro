package com.rodrigoguerrero.ui.feature.details.mappers

import com.rodrigoguerrero.ui.feature.details.models.MovieDetails
import kotlinx.collections.immutable.toPersistentList
import javax.inject.Inject
import com.rodrigoguerrero.domain.details.api.models.MovieDetails as DomainMovieDetails

internal class DetailsMapper @Inject constructor() {
    fun toDetails(details: DomainMovieDetails): MovieDetails = MovieDetails(
        title = details.title,
        overview = details.overview,
        tagline = details.tagline,
        budget = details.budget,
        imdbUrl = details.imdbUrl,
        posterUrl = details.posterUrl,
        voteAverage = details.voteAverage,
        voteCount = details.voteCount,
        genres = details.genres.toPersistentList(),
        revenue = details.revenue,
        runtime = details.runtime,
        status = details.status,
        releaseDate = details.releaseDate.orEmpty(),
    )
}
