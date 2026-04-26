package com.rodrigoguerrero.ui.home.middlewares

import app.cash.turbine.test
import com.rodrigoguerrero.domain.home.api.GetHomeContentInteractor
import com.rodrigoguerrero.domain.home.api.models.HomeContent
import com.rodrigoguerrero.ui.common.mvi.MviDispatcher
import com.rodrigoguerrero.ui.home.mappers.GenreMapper
import com.rodrigoguerrero.ui.home.mappers.TrendingMovieMapper
import com.rodrigoguerrero.ui.home.models.Genre
import com.rodrigoguerrero.ui.home.models.TrendingMovie
import com.rodrigoguerrero.ui.home.mvi.HomeAction
import com.rodrigoguerrero.ui.home.mvi.HomeState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertInstanceOf
import com.rodrigoguerrero.domain.home.api.models.Genre as DomainGenre
import com.rodrigoguerrero.domain.home.api.models.TrendingMovie as DomainTrendingMovie

@OptIn(ExperimentalCoroutinesApi::class)
internal class HomeMiddlewareTest {
    private val unconfinedTestDispatcher = UnconfinedTestDispatcher()

    private val testDispatcher = object : MviDispatcher<HomeAction> {
        val actionFlow = MutableSharedFlow<HomeAction>(extraBufferCapacity = 64)
        override fun dispatch(action: HomeAction) {
            actionFlow.tryEmit(action)
        }
    }

    private val interactor = mockk<GetHomeContentInteractor>()
    private val trendingMovieMapper = mockk<TrendingMovieMapper>()
    private val genreMapper = mockk<GenreMapper>()
    private lateinit var subject: HomeMiddleware

    @BeforeEach
    fun setup() {
        subject = HomeMiddleware(
            interactor = interactor,
            trendingMovieMapper = trendingMovieMapper,
            genreMapper = genreMapper,
        ).apply {
            setDispatcher(testDispatcher)
        }
    }

    @Test
    @DisplayName(
        """
            Given a home content with trending movies and genres from the interactor
            When processing the OnLoad action
            Then OnDataLoaded action is dispatched with correct data and interactor, 
            trendingMovieMapper and genreMapper are called with correct data
        """
    )
    fun onLoadSuccessTest() {
        val domainTrendingMovies = listOf(mockk<DomainTrendingMovie>())
        val domainGenres = listOf(mockk<DomainGenre>())
        val domainHomeContent = mockk<HomeContent> {
            every { trendingMovies } returns domainTrendingMovies
            every { genres } returns domainGenres
        }
        val uiTrendingMovies = persistentListOf(mockk<TrendingMovie>())
        val uiGenres = persistentListOf(mockk<Genre>())

        coEvery { interactor.getTrendingMovies() } returns Result.success(domainHomeContent)
        every { trendingMovieMapper.toTrendingMovies(any()) } returns uiTrendingMovies
        every { genreMapper.toGenres(any()) } returns uiGenres

        runTest(unconfinedTestDispatcher) {
            subject.apply { setCoroutineScope(this@runTest) }

            testDispatcher.actionFlow.test {
                subject.process(
                    state = HomeState.initial,
                    action = HomeAction.OnLoad,
                )

                val result = awaitItem() as? HomeAction.OnDataLoaded
                assertAll(
                    { assertEquals(uiGenres, result?.genres) },
                    { assertEquals(uiTrendingMovies, result?.trendingMovies) },
                    { verify(exactly = 1) { trendingMovieMapper.toTrendingMovies(domainTrendingMovies) } },
                    { verify(exactly = 1) { genreMapper.toGenres(domainGenres) } },
                    { coVerify(exactly = 1) { interactor.getTrendingMovies() } },
                )
            }
        }
    }

    @Test
    @DisplayName(
        """
            Given the interactor returns an error
            When processing the OnLoad action
            Then OnDataLoadFailed action is dispatched
        """
    )
    fun onLoadFailureTest() {
        coEvery { interactor.getTrendingMovies() } returns Result.failure(Exception())

        runTest(unconfinedTestDispatcher) {
            subject.apply { setCoroutineScope(this@runTest) }

            testDispatcher.actionFlow.test {
                subject.process(
                    state = HomeState.initial,
                    action = HomeAction.OnLoad,
                )

                assertInstanceOf<HomeAction.OnDataLoadFailed>(awaitItem())
            }
        }
    }
}
