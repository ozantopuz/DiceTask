package com.ozantopuz.dicetask.data.remote.datasource.artists

import com.ozantopuz.dicetask.data.Result
import com.ozantopuz.dicetask.data.remote.mapper.ArtistDomainMapper
import com.ozantopuz.dicetask.data.remote.service.ArtistService
import com.ozantopuz.dicetask.domain.entity.Artist
import com.ozantopuz.dicetask.domain.usecase.ArtistsParams
import javax.inject.Inject

class ArtistsRemoteDataSourceImpl @Inject constructor(
    private val service: ArtistService,
    private val mapper: ArtistDomainMapper
) : ArtistsRemoteDataSource {

    override suspend fun fetchArtists(params: ArtistsParams): Result<List<Artist>> =
        try {
            val response = service.getArtists(params.query, params.offset, params.limit)
            val list = response.artists?.map { mapper.map(it) } ?: listOf()
            Result.Success(list)
        } catch (e: Exception) {
            Result.Error(e)
        }
}
