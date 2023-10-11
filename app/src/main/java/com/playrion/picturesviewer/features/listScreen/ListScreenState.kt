package com.playrion.picturesviewer.features.listScreen

import com.playrion.picturesviewer.domain.models.FlickrPicture

sealed class ListScreenState {
    data object Loading : ListScreenState()
    data class Success(val pictureList: List<FlickrPicture?>) : ListScreenState()
    data class Error(val message: String) : ListScreenState()
}
