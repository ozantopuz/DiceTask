package com.ozantopuz.dicetask.di

import com.ozantopuz.dicetask.data.remote.mapper.ArtistDomainMapper
import com.ozantopuz.dicetask.data.remote.mapper.ReleaseDomainMapper
import com.ozantopuz.dicetask.domain.mapper.ArtistViewItemMapper
import com.ozantopuz.dicetask.domain.mapper.ReleaseViewItemMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Provides
    fun providesArtistDomainMapper(): ArtistDomainMapper = ArtistDomainMapper()

    @Provides
    fun providesArtistViewItemMapper(): ArtistViewItemMapper = ArtistViewItemMapper()

    @Provides
    fun providesReleaseDomainMapper(): ReleaseDomainMapper = ReleaseDomainMapper()

    @Provides
    fun providesReleaseViewItemMapper(): ReleaseViewItemMapper = ReleaseViewItemMapper()
}