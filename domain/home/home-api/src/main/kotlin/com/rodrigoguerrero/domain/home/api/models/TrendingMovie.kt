package com.rodrigoguerrero.domain.home.api.models

data class TrendingMovie(
    val title: String,
    val imageUrl: String,
    val genres: List<Genre>,
)
