package com.ozantopuz.dicetask.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ozantopuz.dicetask.R
import com.ozantopuz.dicetask.data.Result
import com.ozantopuz.dicetask.databinding.FragmentMainBinding
import com.ozantopuz.dicetask.util.delegate.viewBinding
import com.ozantopuz.dicetask.util.extension.findLastVisibleItemPosition
import com.ozantopuz.dicetask.util.extension.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()
    private val adapter: ArtistAdapter by lazy {
        ArtistAdapter { viewItem ->
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailFragment(viewItem)
            )
        }
    }

    private val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val lastVisibleItemPosition = recyclerView.findLastVisibleItemPosition()
            viewModel.paginateArtists(lastVisibleItemPosition)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()

        with(binding.recyclerView) {
            adapter = this@MainFragment.adapter
            addOnScrollListener(recyclerViewOnScrollListener)
        }

        binding.editText.requestFocus()

        binding.editText.doAfterTextChanged { query ->
            viewModel.searchArtist(query.toString())
            binding.progressBar.apply { if (!isVisible) isVisible = true }
        }
    }

    private fun observeViewModel() {
        viewModel.artistsLiveData.observe(viewLifecycleOwner, { pair ->
            binding.progressBar.isVisible = false
            val isPaginate = pair.first
            when (val result = pair.second) {
                is Result.Error -> context.showErrorDialog(result.exception.localizedMessage)
                is Result.Success ->
                    if (isPaginate) adapter.addList(result.data) else adapter.setList(result.data)
            }
        })
    }
}