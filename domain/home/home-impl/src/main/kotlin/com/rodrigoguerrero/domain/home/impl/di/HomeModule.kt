package com.rodrigoguerrero.domain.home.impl.di

import com.rodrigoguerrero.domain.home.api.GetHomeContentInteractor
import com.rodrigoguerrero.domain.home.impl.GetHomeContentInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface HomeModule {
    @Binds
    fun bindGetHomeContentInteractorImpl(impl: GetHomeContentInteractorImpl): GetHomeContentInteractor
}
