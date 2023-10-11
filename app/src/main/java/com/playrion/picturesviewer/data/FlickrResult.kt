package com.playrion.picturesviewer.data

import com.playrion.picturesviewer.domain.models.FlickrPicture

sealed class FlickrResult {
    data class Success(val list: List<FlickrPicture?>) : FlickrResult()
    data class Error(val exception: Exception) : FlickrResult()
}
