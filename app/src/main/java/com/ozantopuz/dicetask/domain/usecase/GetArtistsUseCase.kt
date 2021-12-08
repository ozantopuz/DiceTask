package com.ozantopuz.dicetask.domain.usecase

import com.ozantopuz.dicetask.data.Result
import com.ozantopuz.dicetask.data.succeeded
import com.ozantopuz.dicetask.domain.mapper.ArtistViewItemMapper
import com.ozantopuz.dicetask.domain.repository.ArtistRepository
import com.ozantopuz.dicetask.ui.entity.ArtistViewItem
import com.ozantopuz.dicetask.util.usecase.Params
import com.ozantopuz.dicetask.util.usecase.UseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GetArtistsUseCase @Inject constructor(
    private val repository: ArtistRepository,
    private val mapper: ArtistViewItemMapper
) : UseCase.FlowUseCase<ArtistsParams, List<ArtistViewItem>> {

    override suspend fun execute(params: ArtistsParams): Result<List<ArtistViewItem>> =
        try {
            val result = repository.fetchArtists(params)
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

data class ArtistsParams(
    val query: String,
    val offset: Int,
    val limit: Int,
) : Params()
