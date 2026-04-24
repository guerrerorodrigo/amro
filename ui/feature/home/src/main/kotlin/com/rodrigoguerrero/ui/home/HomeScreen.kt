package com.rodrigoguerrero.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodrigoguerrero.ui.home.mvi.HomeViewModel

/**
 * [Composable] that holds the home screen
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    HomeScreenInternal(modifier = modifier)
}

@Composable
internal fun HomeScreenInternal(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
}
