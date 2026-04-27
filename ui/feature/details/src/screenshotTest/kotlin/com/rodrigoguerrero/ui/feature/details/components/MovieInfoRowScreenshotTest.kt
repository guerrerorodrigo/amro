package com.rodrigoguerrero.ui.feature.details.components

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.ScreenshotTestPreviews

internal class MovieInfoRowScreenshotTest {

    @PreviewTest
    @ScreenshotTestPreviews
    @Composable
    fun MovieInfoRowTest() {
        PreviewBox {
            MovieInfoRow(
                voteAverage = testMovieDetails.voteAverage,
                runtime = testMovieDetails.runtime,
                releaseDate = testMovieDetails.releaseDate,
            )
        }
    }
}
