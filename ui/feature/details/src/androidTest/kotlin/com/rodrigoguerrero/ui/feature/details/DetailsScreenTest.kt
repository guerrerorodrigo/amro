package com.rodrigoguerrero.ui.feature.details

import androidx.compose.ui.test.junit4.v2.createComposeRule
import com.rodrigoguerrero.ui.feature.details.components.testGenres
import org.junit.Rule
import org.junit.Test

internal class DetailsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun detailsScreenTest() {
        composeTestRule.setContent {
            MovieDetailsContent(
                state = testMovieDetailsState,
                onAction = {},
                onBack = {},
            )
        }

        detailsScreen(composeTestRule) {
            assertMovieTitleIsDisplayed("title")
            assertMovieTaglineIsDisplayed("\"tagline\"")
            assertMovieInfoRowIsDisplayed("8.40 • 98 min • May 31, 2017")
            assertGenresStripIsDisplayed(testGenres)
            assertImdbButtonIsDisplayed()
            assertOverviewIsDisplayed("overview")
            scroll()
            assertStatusIsDisplayed("released")
            assertBudgetIsDisplayed("$5000000")
            assertRevenueIsDisplayed("$33330000")
            assertNumberOfVotesIsDisplayed("12345")
            assertRatingIsDisplayed("8.4")
        }
    }
}
