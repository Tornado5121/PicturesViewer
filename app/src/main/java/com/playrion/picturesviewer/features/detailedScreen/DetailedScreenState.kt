package com.playrion.picturesviewer.features.detailedScreen

import com.playrion.picturesviewer.domain.models.FlickrPicture

sealed class DetailedScreenState {
    data object Loading : DetailedScreenState()
    data class Success(val item: Pair<List<FlickrPicture?>, Int>) : DetailedScreenState()
    data class Error(val message: String) : DetailedScreenState()
}
