package com.rodrigoguerrero.repository.movies.impl.di

import com.rodrigoguerrero.repository.movies.api.MovieGenresRepository
import com.rodrigoguerrero.repository.movies.api.MoviesRepository
import com.rodrigoguerrero.repository.movies.impl.MovieGenresRepositoryImpl
import com.rodrigoguerrero.repository.movies.impl.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface MoviesRepositoryModule {
    @Binds
    fun bindMoviesRepositoryImpl(impl: MoviesRepositoryImpl): MoviesRepository

    @Binds
    @Singleton
    fun bindMovieGenresRepositoryImpl(impl: MovieGenresRepositoryImpl): MovieGenresRepository
}
