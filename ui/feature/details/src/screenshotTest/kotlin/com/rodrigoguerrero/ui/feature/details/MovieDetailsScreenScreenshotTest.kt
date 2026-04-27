package com.rodrigoguerrero.ui.feature.details

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.ScreenshotTestPreviews

internal class MovieDetailsScreenScreenshotTest {

    @PreviewTest
    @ScreenshotTestPreviews
    @Composable
    fun MovieDetailsScreenTest() {
        PreviewBox {
            MovieDetailsContent(
                state = testMovieDetailsState,
                onBack = {},
                onAction = {},
            )
        }
    }

    @PreviewTest
    @ScreenshotTestPreviews
    @Composable
    fun MovieDetailsScreenLoadingTest() {
        PreviewBox {
            MovieDetailsContent(
                state = testLoadingMovieDetailsState,
                onBack = {},
                onAction = {},
            )
        }
    }

    @PreviewTest
    @ScreenshotTestPreviews
    @Composable
    fun MovieDetailsScreenErrorTest() {
        PreviewBox {
            MovieDetailsContent(
                state = testErrorMovieDetailsState,
                onBack = {},
                onAction = {},
            )
        }
    }
}