package com.ozantopuz.dicetask.domain.repository

import com.ozantopuz.dicetask.data.Result
import com.ozantopuz.dicetask.domain.entity.Artist
import com.ozantopuz.dicetask.domain.entity.Release
import com.ozantopuz.dicetask.domain.usecase.ArtistsParams
import com.ozantopuz.dicetask.domain.usecase.ReleasesParams

interface ArtistRepository {
    suspend fun fetchArtists(params: ArtistsParams): Result<List<Artist>>
    suspend fun fetchReleases(params: ReleasesParams): Result<List<Release>>
}