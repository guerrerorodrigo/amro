package com.rodrigoguerrero.ui.home

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick

internal class HomeScreenRobot(
    private val rule: ComposeTestRule,
) {
    fun assertMovieTitleIsDisplayed(value: String) {
        rule.assertIsDisplayed(value)
    }

    fun assertDropDownDisplayedWithOption(option: String) {
        rule.assertIsDisplayed(option)
    }

    fun assertGenreDisplayed(value: String) {
        rule.assertIsDisplayed(value)
    }

    fun assertDropDownMenuItemDisplayed(value: String) {
        rule.assertIsDisplayed(value)
    }

    fun assertDropDownMenuDisplayedWithItemDisplayed(value: String) {
        rule
            .onAllNodesWithText(value)
            .assertCountEquals(2)
    }

    fun clickOnDropDownMenu(value: String) {
        rule.click(value)
    }

    private fun ComposeTestRule.assertIsDisplayed(value: String) {
        onNodeWithText(text = value).assertIsDisplayed()
    }

    private fun ComposeTestRule.click(value: String) {
        onNodeWithText(text = value).performClick()
    }
}

internal fun homeScreen(
    rule: ComposeTestRule,
    block: HomeScreenRobot.() -> Unit
) {
    HomeScreenRobot(rule).apply(block)
}
