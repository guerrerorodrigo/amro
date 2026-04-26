package com.rodrigoguerrero.ui.feature.details.navigation

import kotlinx.serialization.Serializable

/**
 * Routes for the details feature
 */
@Serializable
sealed class DetailsRoutes {
    /**
     * Root route for the details feature
     * @param id the ID of the movie to display the details
     */
    @Serializable
    data class Details(val id: Long) : DetailsRoutes()
}
