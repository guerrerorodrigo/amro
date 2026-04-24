package com.rodrigoguerrero.domain.home.api

import com.rodrigoguerrero.domain.home.api.models.HomeContent

/**
 * Interactor to fetch home content
 */
interface GetHomeContentInteractor {

    /**
     * Gets the trending movies
     * @return a [Result] with the [HomeContent]
     */
    suspend fun getTrendingMovies(): Result<HomeContent>
}
