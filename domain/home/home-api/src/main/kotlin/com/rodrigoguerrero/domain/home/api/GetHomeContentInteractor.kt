package com.rodrigoguerrero.domain.home.api

import com.rodrigoguerrero.domain.home.api.models.HomeContent

private const val TOTAL_PAGES = 5

/**
 * Interactor to fetch home content
 */
interface GetHomeContentInteractor {

    /**
     * Gets the trending movies
     * @return a [Result] with the [HomeContent]
     */
    suspend fun getTrendingMovies(totalPages: Int = TOTAL_PAGES): Result<HomeContent>
}
