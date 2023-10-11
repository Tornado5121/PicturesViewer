package com.playrion.picturesviewer.features.detailedScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playrion.picturesviewer.data.FlickrResult
import com.playrion.picturesviewer.domain.iRepository.FlickrPictureRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailedViewModel(
    private val flickrPictureRepository: FlickrPictureRepository,
    private val id: String
) : ViewModel() {

    private val _state = MutableStateFlow<DetailedScreenState>(DetailedScreenState.Loading)
    val state = _state.asStateFlow()

    init {
        getPicturesSet()
    }

    fun getPicturesSet() {
        viewModelScope.launch {
            when (val result = flickrPictureRepository.getPicturesSet(id)) {
                is FlickrResult.Error -> _state.emit(DetailedScreenState.Error(result.exception.message.toString()))
                is FlickrResult.Success -> {
                    val positionToFocus = result.list.indexOfFirst { it?.id == id }
                    _state.emit(DetailedScreenState.Success(Pair(result.list, positionToFocus)))
                }
            }

        }
    }
}