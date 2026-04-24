package com.rodrigoguerrero.ui.common.mvi.testdata

import com.rodrigoguerrero.ui.common.mvi.Middleware
import com.rodrigoguerrero.ui.common.mvi.MviAction
import com.rodrigoguerrero.ui.common.mvi.Reducer
import com.rodrigoguerrero.ui.common.mvi.State
import io.mockk.mockk

internal sealed class TestState : State {
    object InitialState : TestState()
    object StateAfterFirstAction : TestState()
}

internal sealed class TestAction : MviAction {
    object FirstAction : TestAction()
}

internal val fakeReducer = object : Reducer<TestState, TestAction> {
    override fun reduce(
        state: TestState,
        action: TestAction
    ): TestState {
        return when (action) {
            TestAction.FirstAction -> TestState.StateAfterFirstAction
        }
    }
}
internal val middleware = mockk<Middleware<TestState, TestAction>>(relaxed = true)
internal val anotherMiddleware = mockk<Middleware<TestState, TestAction>>(relaxed = true)
