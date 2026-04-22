package com.rodrigoguerrero.data.source.tmdb.impl.di

import com.rodrigoguerrero.data.source.tmdb.api.MediaDataSource
import com.rodrigoguerrero.data.source.tmdb.impl.BuildConfig
import com.rodrigoguerrero.data.source.tmdb.impl.HttpClientProvider
import com.rodrigoguerrero.data.source.tmdb.impl.TmdbMediaDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TmdbClient

@Module
@InstallIn(SingletonComponent::class)
internal object TmdbProvidesModule {

    @Provides
    @TmdbClient
    fun provideTmdbHttpClient(): HttpClient = HttpClientProvider.createHttpClient(
        httpClientEngine = Android.create(),
        apiKey = BuildConfig.TMDB_API_KEY,
    )
}

@Module
@InstallIn(SingletonComponent::class)
internal interface TmdbModule {
    @Binds
    fun bindMoviesDataSourceImpl(impl: TmdbMediaDataSourceImpl): MediaDataSource
}
