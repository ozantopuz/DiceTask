package com.ozantopuz.dicetask.di

import com.ozantopuz.dicetask.data.remote.datasource.artists.ArtistsRemoteDataSource
import com.ozantopuz.dicetask.data.remote.datasource.artists.ArtistsRemoteDataSourceImpl
import com.ozantopuz.dicetask.data.remote.datasource.releases.ReleasesRemoteDataSource
import com.ozantopuz.dicetask.data.remote.datasource.releases.ReleasesRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindArtistsRemoteDataSource(remoteDataSource: ArtistsRemoteDataSourceImpl): ArtistsRemoteDataSource

    @Binds
    abstract fun bindReleasesRemoteDataSource(remoteDataSource: ReleasesRemoteDataSourceImpl): ReleasesRemoteDataSource
}