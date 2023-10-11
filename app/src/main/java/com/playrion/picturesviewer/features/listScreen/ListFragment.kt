package com.playrion.picturesviewer.features.listScreen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.playrion.picturesviewer.base.BaseFragment
import com.playrion.picturesviewer.databinding.FragmentListBinding
import com.playrion.picturesviewer.domain.models.FlickrPicture
import com.playrion.picturesviewer.extensions.collectOnLifeCycle
import com.playrion.picturesviewer.extensions.showError
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val viewModel by viewModel<ListViewModel>()

    private val picturesAdapter by lazy {
        PicturesListAdapter({
            navigateToDetailed(it)
        }, {
            viewModel.getNewPage()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun setupView() {
        with(binding) {
            picturesList.adapter = picturesAdapter
            picturesList.layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    private fun setupObservers() {
        collectOnLifeCycle(viewModel.state, ::renderSuccessState)
    }

    private fun renderSuccessState(state: ListScreenState) {
        when (state) {
            is ListScreenState.Error -> {
                binding.progressBar.isVisible = false
                showError(state.message) {
                    viewModel.getNewPage()
                }
            }

            ListScreenState.Loading -> {
                binding.progressBar.isVisible = true
            }

            is ListScreenState.Success -> {
                binding.progressBar.isVisible = false
                handleSuccessState(state.pictureList)
            }
        }
    }

    private fun handleSuccessState(list: List<FlickrPicture?>) {
        picturesAdapter.submitList(list)
    }

    private fun navigateToDetailed(flickr: FlickrPicture) {
        findNavController().navigate(
            ListFragmentDirections.actionListFragmentToDetailedFragment(
                flickr
            )
        )
    }
}