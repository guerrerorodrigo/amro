package com.rodrigoguerrero.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.ui.home.mvi.HomeState

internal class HomeScreenSnapshotTest {

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun HomeScreenLightTest() {
        TestComposable(isDarkMode = false, state = testHomeState)
    }

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun HomeScreenDarkTest() {
        TestComposable(isDarkMode = true, state = testHomeState)
    }

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun HomeScreenEmptyLightTest() {
        TestComposable(isDarkMode = false, state = testHomeEmptyState)
    }

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun HomeScreenEmptyDarkTest() {
        TestComposable(isDarkMode = true, state = testHomeEmptyState)
    }

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun HomeScreenLoadingLightTest() {
        TestComposable(isDarkMode = false, state = testHomeLoadingState)
    }

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun HomeScreenLoadingDarkTest() {
        TestComposable(isDarkMode = true, state = testHomeLoadingState)
    }

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun HomeScreenErrorLightTest() {
        TestComposable(isDarkMode = false, state = testErrorHomeState)
    }

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun HomeScreenErrorDarkTest() {
        TestComposable(isDarkMode = true, state = testErrorHomeState)
    }

    @Composable
    private fun TestComposable(
        isDarkMode: Boolean,
        state: HomeState,
    ) {
        PreviewBox(isDarkTheme = isDarkMode) {
            HomeScreenContent(state = state, onAction = {}, onMovieClick = {})
        }
    }
}
