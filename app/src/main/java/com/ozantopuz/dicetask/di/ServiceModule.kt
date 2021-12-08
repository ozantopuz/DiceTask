package com.ozantopuz.dicetask.di

import com.ozantopuz.dicetask.data.remote.service.ArtistService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideArtistService(retrofit: Retrofit): ArtistService = retrofit.create()
}