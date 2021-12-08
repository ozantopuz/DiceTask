package com.ozantopuz.dicetask.data.remote.datasource.artists

import com.ozantopuz.dicetask.data.Result
import com.ozantopuz.dicetask.domain.entity.Artist
import com.ozantopuz.dicetask.domain.usecase.ArtistsParams

interface ArtistsRemoteDataSource {
    suspend fun fetchArtists(params: ArtistsParams): Result<List<Artist>>
}