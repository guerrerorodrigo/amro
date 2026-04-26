package com.rodrigoguerrero.ui.home.navigation

import kotlinx.serialization.Serializable

/**
 * Routes for the home feature
 */
@Serializable
sealed class HomeRoutes {
    /**
     * Root route for the home feature
     */
    @Serializable
    data object Home : HomeRoutes()
}
