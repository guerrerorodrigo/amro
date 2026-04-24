package com.rodrigoguerrero.ui.home.middlewares

import com.rodrigoguerrero.domain.home.api.GetHomeContentInteractor
import com.rodrigoguerrero.domain.home.api.models.HomeContent
import com.rodrigoguerrero.ui.common.mvi.Middleware
import com.rodrigoguerrero.ui.home.mappers.GenreMapper
import com.rodrigoguerrero.ui.home.mappers.TrendingMovieMapper
import com.rodrigoguerrero.ui.home.mvi.HomeAction
import com.rodrigoguerrero.ui.home.mvi.HomeAction.OnDataLoaded
import com.rodrigoguerrero.ui.home.mvi.HomeState
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class HomeMiddleware @Inject constructor(
    private val interactor: GetHomeContentInteractor,
    private val trendingMovieMapper: TrendingMovieMapper,
    private val genreMapper: GenreMapper,
) : Middleware<HomeState, HomeAction>() {
    override fun process(
        state: HomeState,
        action: HomeAction,
    ) {
        when (action) {
            HomeAction.OnLoad -> loadTrendingMovies()
            else -> Unit
        }
    }

    private fun loadTrendingMovies() {
        scope.launch {
            interactor
                .getTrendingMovies()
                .fold(
                    onSuccess = { processSuccess(it) },
                    onFailure = {
                        // TODO implement error handling
                    },
                )
        }
    }

    private fun processSuccess(homeContent: HomeContent) {
        dispatch(
            OnDataLoaded(
                trendingMovies = trendingMovieMapper.toTrendingMovies(
                    trendingMovies = homeContent.trendingMovies,
                ),
                genres = genreMapper.toGenres(genres = homeContent.genres),
            )
        )
    }
}
