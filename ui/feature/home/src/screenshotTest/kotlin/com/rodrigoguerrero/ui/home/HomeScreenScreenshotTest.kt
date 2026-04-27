package com.rodrigoguerrero.ui.home

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.ScreenshotTestPreviews
import com.rodrigoguerrero.ui.home.mvi.HomeState

internal class HomeScreenScreenshotTest {

    @PreviewTest
    @ScreenshotTestPreviews
    @Composable
    fun HomeScreenLightTest() {
        TestComposable(state = testHomeState)
    }

    @PreviewTest
    @ScreenshotTestPreviews
    @Composable
    fun HomeScreenEmptyLightTest() {
        TestComposable(state = testHomeEmptyState)
    }

    @PreviewTest
    @ScreenshotTestPreviews
    @Composable
    fun HomeScreenLoadingLightTest() {
        TestComposable(state = testHomeLoadingState)
    }

    @PreviewTest
    @ScreenshotTestPreviews
    @Composable
    fun HomeScreenErrorLightTest() {
        TestComposable(state = testErrorHomeState)
    }

    @Composable
    private fun TestComposable(state: HomeState, ) {
        PreviewBox() {
            HomeScreenContent(state = state, onAction = {}, onMovieClick = {})
        }
    }
}
