package com.rodrigoguerrero.domain.home.api

import com.rodrigoguerrero.domain.home.api.models.TrendingMovie

interface GetHomeContentInteractor {
    suspend fun getTrendingMovies(): Result<List<TrendingMovie>>
}
