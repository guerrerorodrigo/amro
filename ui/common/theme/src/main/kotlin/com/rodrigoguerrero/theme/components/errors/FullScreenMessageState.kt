package com.rodrigoguerrero.theme.components.errors

import androidx.annotation.StringRes

sealed interface FullScreenMessageState {
    val ctaLabelRes: Int

    data class LocalFullScreenMessage(
        @StringRes val messageRes: Int,
        @StringRes override val ctaLabelRes: Int,
    ) : FullScreenMessageState

    data class RemoteFullScreenMessage(
        val messageRes: String,
        @StringRes override val ctaLabelRes: Int,
    ) : FullScreenMessageState
}
