package com.rodrigoguerrero.domain.home.api.models

/**
 * Encapsulates the genres and trending movies for the home content
 * @param genres [List] of available [Genre]s
 * @param trendingMovies [List] of available [TrendingMovie]s
 */
data class HomeContent(
    val genres: List<Genre>,
    val trendingMovies: List<TrendingMovie>,
)
