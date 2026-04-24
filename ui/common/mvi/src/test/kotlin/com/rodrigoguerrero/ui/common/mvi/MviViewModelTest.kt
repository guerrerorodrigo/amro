package com.rodrigoguerrero.ui.common.mvi

import com.rodrigoguerrero.ui.common.mvi.testdata.TestState
import com.rodrigoguerrero.ui.common.mvi.testdata.anotherMiddleware
import com.rodrigoguerrero.ui.common.mvi.testdata.fakeReducer
import com.rodrigoguerrero.ui.common.mvi.testdata.middleware
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@OptIn(ExperimentalCoroutinesApi::class)
internal class MviViewModelTest {
    @Test
    @DisplayName(
        """
            Given the MviViewModel
            When view model has been created
            Then all middlewares in the view model are setup with correct coroutine scope
        """
    )
    fun initializeTest() {
        MviViewModel(
            initialState = TestState.InitialState,
            reducer = fakeReducer,
            middlewares = listOf(middleware, anotherMiddleware),
        )
        assertAll(
            { verify(exactly = 1) { middleware.setup(any(), any()) } },
            { verify(exactly = 1) { anotherMiddleware.setup(any(), any()) } },
        )
    }
}
