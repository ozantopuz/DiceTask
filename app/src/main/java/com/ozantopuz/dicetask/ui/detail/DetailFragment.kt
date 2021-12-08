package com.ozantopuz.dicetask.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ozantopuz.dicetask.R
import com.ozantopuz.dicetask.data.Result
import com.ozantopuz.dicetask.databinding.FragmentDetailBinding
import com.ozantopuz.dicetask.ui.entity.ArtistViewItem
import com.ozantopuz.dicetask.util.delegate.viewBinding
import com.ozantopuz.dicetask.util.extension.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var viewItem: ArtistViewItem
    private val binding: FragmentDetailBinding by viewBinding()
    private val viewModel: DetailViewModel by viewModels()
    private val adapter: ReleaseAdapter = ReleaseAdapter()
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()

        viewItem = args.viewItem

        with(binding) {
            recyclerView.adapter = adapter
            textViewName.text = viewItem.name
            textViewScore.text = viewItem.score
            textViewCountry.text = viewItem.country
            textViewType.text = viewItem.type
        }

        viewModel.fetchReleases(viewItem.id)
    }

    private fun observeViewModel() {
        viewModel.releasesLiveData.observe(viewLifecycleOwner, { result ->
            binding.progressBar.isVisible = false
            when (result) {
                is Result.Error -> context.showErrorDialog(result.exception.localizedMessage)
                is Result.Success -> {
                    binding.textViewMessageRelease.isVisible = result.data.isNullOrEmpty()
                    adapter.setList(result.data)
                }
            }
        })
    }
}