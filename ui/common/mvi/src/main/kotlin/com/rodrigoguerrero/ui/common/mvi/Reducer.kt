package com.rodrigoguerrero.ui.common.mvi

/**
 * Provides the [Reducer] implementation that will handle the [MviAction]s and return the updated [State]. This should be
 * used for synchronous updates of the [State].
 */
interface Reducer<S: State, A: MviAction> {
    /**
     * Creates a new instance of [State] depending on the [MviAction]
     *
     * @param action the [MviAction] to reduce the [State] with
     * @param state the current [State]
     * @return the reduced [State]
     */
    fun reduce(state: S, action: A): S
}
