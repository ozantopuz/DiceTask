package com.ozantopuz.dicetask.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozantopuz.dicetask.data.Result
import com.ozantopuz.dicetask.domain.usecase.GetReleasesUseCase
import com.ozantopuz.dicetask.domain.usecase.ReleasesParams
import com.ozantopuz.dicetask.ui.entity.ReleaseViewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val releasesUseCase: GetReleasesUseCase
) : ViewModel() {

    private val _releasesLiveData = MutableLiveData<Result<List<ReleaseViewItem>>>()
    val releasesLiveData: LiveData<Result<List<ReleaseViewItem>>>
        get() = _releasesLiveData

    fun fetchReleases(artist: String) {
        val params = ReleasesParams(artist = artist)
        viewModelScope.launch {
            val result = releasesUseCase.execute(params)
            _releasesLiveData.value = result
        }
    }
}