package com.rodrigoguerrero.theme.components.errors

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.theme.AmroTheme
import com.rodrigoguerrero.theme.R
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.WidgetPreviews

/**
 * [Composable] that displays a full screen message
 * @param onClick lambda to execute when clicking the button in the error
 * @param state [FullScreenMessageState] with data to display
 * @param modifier optional [Modifier]
 */
@Composable
fun FullScreenMessage(
    onClick: () -> Unit,
    state: FullScreenMessageState,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AmroTheme.dimens.padding.md),
            modifier = Modifier.padding(horizontal = AmroTheme.dimens.padding.md),
        ) {
            Text(
                text = state.getMessage(),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .focusable(enabled = true),
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onClick,
            ) {
                Text(text = stringResource(state.ctaLabelRes))
            }
        }
    }
}

@Composable
private fun FullScreenMessageState.getMessage() = when (this) {
    is FullScreenMessageState.RemoteFullScreenMessage -> messageRes
    is FullScreenMessageState.LocalFullScreenMessage -> stringResource(messageRes)
}

@WidgetPreviews
@Composable
private fun PreviewFullScreenMessage() {
    PreviewBox {
        FullScreenMessage(
            onClick = {},
            state = FullScreenMessageState.LocalFullScreenMessage(
                messageRes = R.string.generic_error,
                ctaLabelRes = R.string.try_again,
            )
        )
    }
}
