package com.ozantopuz.dicetask.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozantopuz.dicetask.data.Result
import com.ozantopuz.dicetask.data.succeeded
import com.ozantopuz.dicetask.domain.usecase.ArtistsParams
import com.ozantopuz.dicetask.domain.usecase.GetArtistsUseCase
import com.ozantopuz.dicetask.ui.entity.ArtistViewItem
import com.ozantopuz.dicetask.util.extension.ignoreNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(
    private val artistsUseCase: GetArtistsUseCase
) : ViewModel() {

    private var page: Int = 0
    private var artistSize: Int = 0
    private var query: String? = null
    private var isFetchingData: Boolean = false
    private var isLastPage: Boolean = false
    private var isFromPagination: Boolean = false
    private var queryTextChangedJob: Job? = null

    private val _artistsLiveData = MutableLiveData<Pair<Boolean, Result<List<ArtistViewItem>>>>()
    val artistsLiveData: LiveData<Pair<Boolean, Result<List<ArtistViewItem>>>>
        get() = _artistsLiveData

    private fun fetchArtists() {
        if (isFetchingData) return
        isFetchingData = true

        val params = ArtistsParams(
            query = query.ignoreNull(),
            offset = page * PAGINATION_LIMIT,
            limit = PAGINATION_LIMIT
        )
        viewModelScope.launch {
            val result = artistsUseCase.execute(params)
            if (result.succeeded) {
                artistSize += (result as Result.Success).data.size
                if (result.data.isNotEmpty()) page++ else isLastPage = true
            }
            _artistsLiveData.value = Pair(isFromPagination, result)
            isFetchingData = false
        }
    }

    fun paginateArtists(lastVisibleItemPosition: Int) {
        if (lastVisibleItemPosition <= 0 ||
            lastVisibleItemPosition < artistSize - PAGINATION_THRESHOLD ||
            isFetchingData ||
            artistSize < PAGINATION_LIMIT ||
            isLastPage
        ) return
        isFromPagination = true
        fetchArtists()
    }

    fun searchArtist(query: String) {
        queryTextChangedJob?.cancel()
        queryTextChangedJob = viewModelScope.launch {
            delay(SEARCH_DELAY)
            this@MainViewModel.query = query
            clearData()

            if (query.isEmpty()) {
                _artistsLiveData.value = Pair(isFromPagination, Result.Success(listOf()))
                return@launch
            }

            fetchArtists()
        }
    }

    private fun clearData() {
        page = 0
        artistSize = 0
        isFromPagination = false
        isLastPage = false
    }

    companion object {
        private const val SEARCH_DELAY = 1_000L
        private const val PAGINATION_LIMIT = 25
        private const val PAGINATION_THRESHOLD = 10
    }
}