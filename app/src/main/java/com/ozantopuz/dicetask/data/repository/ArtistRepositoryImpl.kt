package com.ozantopuz.dicetask.data.repository

import com.ozantopuz.dicetask.data.Result
import com.ozantopuz.dicetask.data.remote.datasource.artists.ArtistsRemoteDataSource
import com.ozantopuz.dicetask.data.remote.datasource.releases.ReleasesRemoteDataSource
import com.ozantopuz.dicetask.domain.entity.Artist
import com.ozantopuz.dicetask.domain.entity.Release
import com.ozantopuz.dicetask.domain.repository.ArtistRepository
import com.ozantopuz.dicetask.domain.usecase.ArtistsParams
import com.ozantopuz.dicetask.domain.usecase.ReleasesParams
import javax.inject.Inject

class ArtistRepositoryImpl @Inject constructor(
    private val artistsRemoteDataSource: ArtistsRemoteDataSource,
    private val releasesRemoteDataSource: ReleasesRemoteDataSource
) : ArtistRepository {

    override suspend fun fetchArtists(params: ArtistsParams): Result<List<Artist>> =
        artistsRemoteDataSource.fetchArtists(params)

    override suspend fun fetchReleases(params: ReleasesParams): Result<List<Release>> =
        releasesRemoteDataSource.fetchReleases(params)
}