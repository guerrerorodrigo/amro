package com.rodrigoguerrero.ui.common.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow

/**
 * Base [ViewModel] that contains logic to handle MVI.
 *
 * @param S The [State] managed by this [ViewModel].
 * @param A The [MviAction]s this [ViewModel] handles.
 *
 * @param initialState the initial [State] of this [ViewModel].
 * @param reducer the [Reducer] to use for this [ViewModel].
 * @param router the [Router] to use for this [ViewModel].
 * @param middlewares the list of [Middleware]s to use for this [ViewModel].
 * @constructor Creates a new [MviViewModel] instance.
 */
open class MviViewModel<S : State, A : MviAction>(
    initialState: S,
    reducer: Reducer<S, A>,
    router: Router<A>? = null,
    private val middlewares: List<Middleware<S, A>> = emptyList(),
) : ViewModel() {

    private val stateReducer: MviOrchestrator<S, A> = MviOrchestrator(
        initialState = initialState,
        scope = viewModelScope,
        reducer = reducer,
        middlewares = middlewares,
        router = router,
    )

    /** [StateFlow] that contains the reduced state [S] */
    val state: StateFlow<S> = stateReducer

    init {
        middlewares.forEach { middleware ->
            middleware.setup(
                scope = viewModelScope,
                dispatcher = stateReducer,
            )
        }
    }

    /**
     * Pushes an [MviAction] to the MVI queue to be processed. [MviAction]s are processed sequentially,
     * on a first come, first served basis.
     *
     * @param action the [MviAction] to process
     */
    fun handleAction(action: A) {
        stateReducer.dispatch(action)
    }
}
