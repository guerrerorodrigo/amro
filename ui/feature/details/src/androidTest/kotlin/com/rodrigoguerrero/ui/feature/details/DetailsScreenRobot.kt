package com.rodrigoguerrero.ui.feature.details

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo

internal class DetailsScreenRobot(
    private val rule: ComposeTestRule,
) {
    fun assertMovieTitleIsDisplayed(value: String) {
        rule.assertIsDisplayed(value)
    }

    fun assertMovieTaglineIsDisplayed(option: String) {
        rule.assertIsDisplayed(option)
    }

    fun assertMovieInfoRowIsDisplayed(value: String) {
        rule.assertIsDisplayed(value)
    }

    fun assertGenresStripIsDisplayed(values: List<String>) {
        values.forEach { value -> rule.assertIsDisplayed(value) }
    }

    fun assertImdbButtonIsDisplayed() {
        rule.assertIsDisplayed("Open in IMDB")
    }

    fun assertOverviewIsDisplayed(value: String) {
        rule.assertIsDisplayed("Overview")
        rule.assertIsDisplayed(value)
    }

    fun scroll() {
        rule.onNodeWithText("12345").performScrollTo()
    }

    fun assertStatusIsDisplayed(value: String) {
        rule.assertIsDisplayed("Status of This Movie")
        rule.assertIsDisplayed(value)
    }

    fun assertBudgetIsDisplayed(value: String) {
        rule.assertIsDisplayed("Budget")
        rule.assertIsDisplayed(value)
    }

    fun assertRevenueIsDisplayed(value: String) {
        rule.assertIsDisplayed("Revenue")
        rule.assertIsDisplayed(value)
    }

    fun assertNumberOfVotesIsDisplayed(value: String) {
        rule.assertIsDisplayed("Number of Votes")
        rule.assertIsDisplayed(value)
    }

    fun assertRatingIsDisplayed(value: String) {
        rule.assertIsDisplayed("Rating")
        rule.assertIsDisplayed(value)
    }

    private fun ComposeTestRule.assertIsDisplayed(value: String) {
        onNodeWithText(text = value).assertIsDisplayed()
    }
}

internal fun detailsScreen(
    rule: ComposeTestRule,
    block: DetailsScreenRobot.() -> Unit
) {
    DetailsScreenRobot(rule).apply(block)
}
