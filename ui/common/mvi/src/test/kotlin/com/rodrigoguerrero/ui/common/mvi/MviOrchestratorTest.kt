package com.rodrigoguerrero.ui.common.mvi

import app.cash.turbine.test
import com.rodrigoguerrero.ui.common.mvi.testdata.TestAction
import com.rodrigoguerrero.ui.common.mvi.testdata.TestState
import com.rodrigoguerrero.ui.common.mvi.testdata.anotherMiddleware
import com.rodrigoguerrero.ui.common.mvi.testdata.fakeReducer
import com.rodrigoguerrero.ui.common.mvi.testdata.middleware
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertInstanceOf

@OptIn(ExperimentalCoroutinesApi::class)
internal class MviOrchestratorTest {
    private val unconfinedTestDispatcher = UnconfinedTestDispatcher()

    @Test
    @DisplayName(
        """
            When the orchestrator is initialized
            Then it contains the initial state
        """
    )
    fun initialStateTest() = runTest(unconfinedTestDispatcher) {
        val orchestrator = createMviOrchestrator()

        assertInstanceOf<TestState.InitialState>(orchestrator.value)
    }

    @Test
    @DisplayName(
        """
            Given an action
            When the action is dispatched
            Then updated state is received
        """
    )
    fun dispatchActionUpdatedStateTest() = runTest(unconfinedTestDispatcher) {
        val orchestrator = createMviOrchestrator()

        orchestrator.dispatch(action = TestAction.FirstAction)

        orchestrator.test {
            assertInstanceOf<TestState.StateAfterFirstAction>(awaitItem())
        }
    }

    @Test
    @DisplayName(
        """
            Given orchestrator has middlewares
            When dispatching an action
            Then all middlewares are called and updated state is returned
        """
    )
    fun middlewaresCalledTest() = runTest(unconfinedTestDispatcher) {
        val orchestrator = createMviOrchestrator()

        orchestrator.dispatch(action = TestAction.FirstAction)

        orchestrator.test {
            val result = awaitItem()
            assertAll(
                { assertInstanceOf<TestState.StateAfterFirstAction>(result) },
                {
                    verify(exactly = 1) {
                        middleware.process(TestState.InitialState, TestAction.FirstAction)
                    }
                },
                {
                    verify(exactly = 1) {
                        anotherMiddleware.process(
                            TestState.InitialState,
                            TestAction.FirstAction,
                        )
                    }
                },
            )
        }
    }

    private fun TestScope.createMviOrchestrator(): MviOrchestrator<TestState, TestAction> =
        MviOrchestrator(
            initialState = TestState.InitialState,
            reducer = fakeReducer,
            middlewares = listOf(middleware, anotherMiddleware),
            scope = CoroutineScope(UnconfinedTestDispatcher(testScheduler)),
        )
}
