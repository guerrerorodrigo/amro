package com.rodrigoguerrero.ui.common.mvi

/**
 * Dispatches [MviAction]s to be handled by the MVI framework.
 */
interface MviDispatcher<A : MviAction> {
    fun dispatch(action: A)
}
