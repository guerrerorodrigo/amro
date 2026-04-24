package com.rodrigoguerrero.ui.common.mvi

/**
 * Class used to handle the navigation actions in MVI
 */
interface Router<A : MviAction> {
    /**
     * Processes the [MviAction].
     *
     * @param action the [MviAction] to process
     */
    fun process(action: A)
}
