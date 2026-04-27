package com.rodrigoguerrero.ui.feature.details.middlewares

import app.cash.turbine.test
import com.rodrigoguerrero.domain.details.api.GetMovieDetailsInteractor
import com.rodrigoguerrero.ui.common.mvi.MviDispatcher
import com.rodrigoguerrero.ui.feature.details.mappers.DetailsMapper
import com.rodrigoguerrero.ui.feature.details.models.MovieDetails
import com.rodrigoguerrero.ui.feature.details.mvi.DetailsAction
import com.rodrigoguerrero.ui.feature.details.mvi.DetailsState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
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
import com.rodrigoguerrero.domain.details.api.models.MovieDetails as DomainMovieDetails

@OptIn(ExperimentalCoroutinesApi::class)
internal class DetailsMiddlewareTest {
    private val unconfinedTestDispatcher = UnconfinedTestDispatcher()

    private val testDispatcher = object : MviDispatcher<DetailsAction> {
        val actionFlow = MutableSharedFlow<DetailsAction>(extraBufferCapacity = 64)
        override fun dispatch(action: DetailsAction) {
            actionFlow.tryEmit(action)
        }
    }
    private val interactor = mockk<GetMovieDetailsInteractor>()
    private val mapper = mockk<DetailsMapper>()

    private lateinit var subject: DetailsMiddleware

    @BeforeEach
    fun setup() {
        subject = DetailsMiddleware(
            interactor = interactor,
            mapper = mapper,
        ).apply {
            setDispatcher(testDispatcher)
        }
    }

    @Test
    @DisplayName(
        """
            Given interactor returns a successful result
            When processing the OnLoad action
            Then result contains ui movie details and mapper is called with correct data
        """
    )
    fun onLoadSuccessTest() {
        val domainMovieDetails = mockk<DomainMovieDetails>()
        val uiMovieDetails = mockk<MovieDetails>()
        coEvery { interactor.getMovieDetails(any()) } returns Result.success(domainMovieDetails)
        every { mapper.toDetails(any()) } returns uiMovieDetails

        runTest(unconfinedTestDispatcher) {
            subject.apply { setCoroutineScope(this@runTest) }

            testDispatcher.actionFlow.test {
                subject.process(
                    state = DetailsState.initial,
                    action = DetailsAction.OnLoad(id = 123),
                )

                val result = awaitItem() as? DetailsAction.OnDataLoaded
                assertAll(
                    { assertEquals(uiMovieDetails, result?.movieDetails) },
                    { verify(exactly = 1) { mapper.toDetails(domainMovieDetails) } },
                )
            }
        }
    }

    @Test
    @DisplayName(
        """
            Given interactor returns a failure
            When processing onLoad action
            Then OnLoadFailed action is dispatched containing the ID of the movie that failed to load
        """
    )
    fun onLoadFailureTest() {
        coEvery { interactor.getMovieDetails(any()) } returns Result.failure(Exception())

        runTest(unconfinedTestDispatcher) {
            subject.apply { setCoroutineScope(this@runTest) }

            testDispatcher.actionFlow.test {
                subject.process(
                    state = DetailsState.initial,
                    action = DetailsAction.OnLoad(id = 123),
                )

                val result = awaitItem()
                assertAll(
                    { assertInstanceOf<DetailsAction.OnLoadFailed>(result) },
                    { assertEquals(123, (result as? DetailsAction.OnLoadFailed)?.id) },
                )
            }
        }
    }
}
