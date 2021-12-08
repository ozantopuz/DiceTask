package com.ozantopuz.dicetask.di

import com.ozantopuz.dicetask.data.repository.ArtistRepositoryImpl
import com.ozantopuz.dicetask.domain.repository.ArtistRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindArtistRepository(repository: ArtistRepositoryImpl): ArtistRepository
}