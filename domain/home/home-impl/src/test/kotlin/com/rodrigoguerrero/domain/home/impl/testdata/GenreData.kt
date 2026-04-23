package com.rodrigoguerrero.domain.home.impl.testdata

import com.rodrigoguerrero.domain.home.api.models.Genre
import com.rodrigoguerrero.repository.movies.api.models.Genre as RepoGenre

internal val genre = RepoGenre(id = 1, name = "genre")
internal val anotherGenre = RepoGenre(id = 2, name = "another genre")
internal val expectedGenre = Genre(id = 1, name = "genre")
internal val anotherExpectedGenre = Genre(id = 2, name = "another genre")
internal val genres = listOf(expectedGenre, anotherExpectedGenre)
