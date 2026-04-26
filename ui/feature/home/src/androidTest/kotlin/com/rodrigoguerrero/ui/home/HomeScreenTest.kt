package com.rodrigoguerrero.ui.home

import androidx.compose.ui.test.junit4.v2.createComposeRule
import org.junit.Rule
import org.junit.Test

internal class HomeScreenTest  {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreenTest() {
        composeTestRule.setContent {
            HomeScreenContent(
                state = testHomeState,
                onAction = {},
                onMovieClick = {},
            )
        }

        homeScreen(composeTestRule) {
            assertMovieTitleIsDisplayed(value = "title")
            assertMovieTitleIsDisplayed(value = "title2")
            assertMovieTitleIsDisplayed(value = "title3")

            assertDropDownDisplayedWithOption(option = "Title")

            assertGenreDisplayed("Action")
            assertGenreDisplayed("Comedy")

            clickOnDropDownMenu("Title")

            assertDropDownMenuItemDisplayed("Popularity")
            assertDropDownMenuDisplayedWithItemDisplayed("Title")
            assertDropDownMenuItemDisplayed("Release date")
        }
    }
}
