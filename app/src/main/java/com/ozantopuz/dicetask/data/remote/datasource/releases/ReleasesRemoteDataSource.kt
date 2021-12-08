package com.ozantopuz.dicetask.data.remote.datasource.releases

import com.ozantopuz.dicetask.data.Result
import com.ozantopuz.dicetask.domain.entity.Release
import com.ozantopuz.dicetask.domain.usecase.ReleasesParams

interface ReleasesRemoteDataSource {
    suspend fun fetchReleases(params: ReleasesParams): Result<List<Release>>
}