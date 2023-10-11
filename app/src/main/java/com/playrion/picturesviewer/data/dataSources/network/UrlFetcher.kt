package com.playrion.picturesviewer.data.dataSources.network

import com.playrion.picturesviewer.data.dataSources.network.models.picturesInfo.PicturesInfo
import com.playrion.picturesviewer.data.dataSources.network.models.picturesList.FlickrPictureResponse

interface UrlFetcher {

    suspend fun getPictureList(): FlickrPictureResponse
    suspend fun getPictureInfo(id: String): PicturesInfo
}