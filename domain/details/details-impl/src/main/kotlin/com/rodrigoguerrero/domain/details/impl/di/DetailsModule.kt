package com.rodrigoguerrero.domain.details.impl.di

import com.rodrigoguerrero.domain.details.api.GetMovieDetailsInteractor
import com.rodrigoguerrero.domain.details.impl.GetMovieDetailsInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface DetailsModule {
    @Binds
    fun bindGetMovieDetailsInteractorImpl(impl: GetMovieDetailsInteractorImpl): GetMovieDetailsInteractor
}
