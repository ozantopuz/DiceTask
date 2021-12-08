package com.ozantopuz.dicetask.data.remote.datasource.releases

import com.ozantopuz.dicetask.data.Result
import com.ozantopuz.dicetask.data.remote.mapper.ReleaseDomainMapper
import com.ozantopuz.dicetask.data.remote.service.ArtistService
import com.ozantopuz.dicetask.domain.entity.Release
import com.ozantopuz.dicetask.domain.usecase.ReleasesParams
import javax.inject.Inject

class ReleasesRemoteDataSourceImpl @Inject constructor(
    private val service: ArtistService,
    private val mapper: ReleaseDomainMapper
) : ReleasesRemoteDataSource {

    override suspend fun fetchReleases(params: ReleasesParams): Result<List<Release>> =
        try {
            val response = service.getReleases(params.artist)
            val list = response.releases?.map { mapper.map(it) } ?: listOf()
            Result.Success(list)
        } catch (e: Exception) {
            Result.Error(e)
        }
}
