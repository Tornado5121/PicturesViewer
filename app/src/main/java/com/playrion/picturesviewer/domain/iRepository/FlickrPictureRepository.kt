package com.playrion.picturesviewer.domain.iRepository

import com.playrion.picturesviewer.data.FlickrResult

interface FlickrPictureRepository {

    suspend fun getFlickrList(): FlickrResult
    suspend fun getNewPicturesPage(): FlickrResult
    suspend fun getPicturesSet(id: String): FlickrResult
    suspend fun clearDataBase()
}