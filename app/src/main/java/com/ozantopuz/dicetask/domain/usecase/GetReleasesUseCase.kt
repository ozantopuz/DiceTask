package com.ozantopuz.dicetask.domain.usecase

import com.ozantopuz.dicetask.data.Result
import com.ozantopuz.dicetask.data.succeeded
import com.ozantopuz.dicetask.domain.mapper.ReleaseViewItemMapper
import com.ozantopuz.dicetask.domain.repository.ArtistRepository
import com.ozantopuz.dicetask.ui.entity.ReleaseViewItem
import com.ozantopuz.dicetask.util.usecase.Params
import com.ozantopuz.dicetask.util.usecase.UseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GetReleasesUseCase @Inject constructor(
    private val repository: ArtistRepository,
    private val mapper: ReleaseViewItemMapper
) : UseCase.FlowUseCase<ReleasesParams, List<ReleaseViewItem>> {

    override suspend fun execute(params: ReleasesParams): Result<List<ReleaseViewItem>> =
        try {
            val result = repository.fetchReleases(params)
            if (result.succeeded) {
                result as Result.Success
                val list = result.data.map { mapper.map(it) }
                Result.Success(list)
            } else {
                result as Result.Error
                Result.Error(result.exception)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
}

data class ReleasesParams(
    val artist: String
) : Params()
