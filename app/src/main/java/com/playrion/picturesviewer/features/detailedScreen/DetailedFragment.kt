package com.playrion.picturesviewer.features.detailedScreen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.playrion.picturesviewer.base.BaseFragment
import com.playrion.picturesviewer.databinding.FragmentDetailedBinding
import com.playrion.picturesviewer.extensions.collectOnLifeCycle
import com.playrion.picturesviewer.extensions.showError
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailedFragment : BaseFragment<FragmentDetailedBinding>(FragmentDetailedBinding::inflate) {

    private val pagerAdapter by lazy { DetailedPicturesPagerAdapter() }
    private val navArgs by navArgs<DetailedFragmentArgs>()
    private val viewModel by viewModel<DetailedViewModel> { parametersOf(navArgs.flickr.id) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        with(binding) {
            detailedPictures.adapter = pagerAdapter
        }
    }

    private fun setupObservers() {
        collectOnLifeCycle(viewModel.state, ::handleState)
    }

    private fun handleState(state: DetailedScreenState) {
        when (state) {
            is DetailedScreenState.Error -> {
                binding.progressBar.isVisible = false
                showError(state.message) {
                    viewModel.getPicturesSet()
                }
            }

            DetailedScreenState.Loading -> {
                binding.progressBar.isVisible = true
            }

            is DetailedScreenState.Success -> {
                binding.progressBar.isVisible = false
                pagerAdapter.submitList(state.item.first)
                binding.detailedPictures.setCurrentItem(state.item.second, false)
            }
        }
    }
}