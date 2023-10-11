package com.playrion.picturesviewer.features.listScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playrion.picturesviewer.data.FlickrResult
import com.playrion.picturesviewer.domain.iRepository.FlickrPictureRepository
import com.playrion.picturesviewer.domain.models.FlickrPicture
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val flickrPictureRepository: FlickrPictureRepository
) : ViewModel() {

    private val _state = MutableStateFlow<ListScreenState>(ListScreenState.Loading)
    val state = _state.asStateFlow()

    private var list = listOf<FlickrPicture?>()

    init {
        getActualPictureList()
    }

    fun getNewPage() {
        viewModelScope.launch {
            _state.emit(ListScreenState.Loading)
            when (val result = flickrPictureRepository.getNewPicturesPage()) {
                is FlickrResult.Error -> _state.emit(ListScreenState.Error(result.exception.message.toString()))
                is FlickrResult.Success -> {
                    list += result.list
                    _state.emit(ListScreenState.Success(list))
                }
            }
        }
    }

    private fun getActualPictureList() {
        viewModelScope.launch {
            _state.emit(ListScreenState.Loading)
            when (val preSavedPicturesList = flickrPictureRepository.getFlickrList()) {
                is FlickrResult.Error -> _state.emit(ListScreenState.Error(preSavedPicturesList.exception.message.toString()))
                is FlickrResult.Success -> _state.emit(ListScreenState.Success(preSavedPicturesList.list))
            }
            flickrPictureRepository.clearDataBase()
            _state.emit(ListScreenState.Loading)
            when (val result = flickrPictureRepository.getNewPicturesPage()) {
                is FlickrResult.Error -> _state.emit(ListScreenState.Error(result.exception.message.toString()))
                is FlickrResult.Success -> {
                    list += result.list
                    _state.emit(ListScreenState.Success(list))
                }
            }
        }
    }
}