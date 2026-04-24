package com.rodrigoguerrero.ui.common.mvi

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineScope


/**
 * Class used to handle asynchronous [MviAction]s in the MVI.
 */
abstract class Middleware<S: State, A: MviAction> {
    protected lateinit var scope: CoroutineScope
    /**
     * The [CoroutineScope] to run the [Middleware] long running tasks on.
     */
    private lateinit var dispatcher: MviDispatcher<A>

    internal fun setup(scope: CoroutineScope, dispatcher: MviDispatcher<A>) {
        this.scope = scope
        this.dispatcher = dispatcher
    }

    /**
     * Processes the [MviAction].
     *
     * @param state - the current [State]
     * @param action - the [MviAction] to process
     */
    abstract fun process(state: S, action: A)

    /**
     * Dispatches the [MviAction] using the [MviDispatcher].
     */
    protected fun dispatch(action: A) = dispatcher.dispatch(action)

    /**
     * Overrides the [MviDispatcher] used in this [Middleware]
     *
     * @param dispatcher the new [MviDispatcher] to use for actions
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun setDispatcher(dispatcher: MviDispatcher<A>) {
        this.dispatcher = dispatcher
    }

    /**
     * Overrides the [CoroutineScope] used in this [Middleware]
     *
     * @param coroutineScope the new [CoroutineScope] to use for asynchronous work
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun setCoroutineScope(coroutineScope: CoroutineScope) {
        scope = coroutineScope
    }
}
