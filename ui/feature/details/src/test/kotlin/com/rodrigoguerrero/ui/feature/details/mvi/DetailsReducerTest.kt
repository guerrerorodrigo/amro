package com.rodrigoguerrero.ui.feature.details.mvi

import com.rodrigoguerrero.theme.components.errors.FullScreenMessageState.LocalFullScreenMessage
import com.rodrigoguerrero.ui.feature.details.R
import com.rodrigoguerrero.ui.feature.details.models.MovieDetails
import com.rodrigoguerrero.ui.feature.details.testMovieDetailsState
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertInstanceOf
import org.junit.jupiter.api.assertNull

internal class DetailsReducerTest {
    private val subject = DetailsReducer()

    @Test
    @DisplayName(
        """
            Given a state with data and full screen message state
            When reducing OnLoad action
            Then isLoading is true, full screen message is null, and movie details state is the 
            initial value
        """
    )
    fun onLoadTest() {
        val result = subject.reduce(
            state = testMovieDetailsState.copy(
                fullScreenMessageState = mockk(),
            ),
            action = DetailsAction.OnLoad(id = 123),
        )

        assertAll(
            { assertTrue(result.isLoading) },
            { assertNull(result.fullScreenMessageState) },
            { assertEquals(MovieDetails.initial, result.movieDetails) },
        )
    }

    @Test
    @DisplayName(
        """
            Given an initial state
            When reducing OnDataLoaded action with movie details data
            Then loading is false, and result has movie details and null full screen message
        """
    )
    fun onDataLoadedTest() {
        val movieDetails = mockk<MovieDetails>()
        val result = subject.reduce(
            state = DetailsState.initial.copy(fullScreenMessageState = mockk()),
            action = DetailsAction.OnDataLoaded(movieDetails = movieDetails),
        )

        assertAll(
            { assertFalse(result.isLoading) },
            { assertEquals(movieDetails, result.movieDetails) },
            { assertNull(result.fullScreenMessageState) },
        )
    }

    @Test
    @DisplayName(
        """
            Given an initial state
            When reducing OnLoadFailed action with an id
            Then result contains the ID, isLoading is false, movie details are initial value
            and FullScreenMessageState contains correct error values
        """
    )
    fun onLoadFailedTest() {
        val result = subject.reduce(
            state = DetailsState.initial,
            action = DetailsAction.OnLoadFailed(id = 123),
        )

        assertAll(
            { assertEquals(123, result.id) },
            { assertFalse(result.isLoading) },
            { assertEquals(MovieDetails.initial, result.movieDetails) },
            { assertInstanceOf<LocalFullScreenMessage>(result.fullScreenMessageState) },
            { assertEquals(R.string.loading_error, (result.fullScreenMessageState as? LocalFullScreenMessage)?.messageRes)},
            { assertEquals(R.string.try_again, (result.fullScreenMessageState as? LocalFullScreenMessage)?.ctaLabelRes)},
        )
    }
}
