package com.rodrigoguerrero.ui.home.testdata

import com.rodrigoguerrero.ui.home.models.Genre
import kotlinx.collections.immutable.persistentListOf
import com.rodrigoguerrero.domain.home.api.models.Genre as DomainGenre

internal val domainGenre = DomainGenre(
    id = 1,
    name = "genre name",
)

internal val expectedGenre = Genre(
    id = 1,
    name = "genre name",
)

internal val uiGenre = Genre(
    id = 2,
    name = "another genre",
)

internal val uiGenres = persistentListOf(expectedGenre, uiGenre)
