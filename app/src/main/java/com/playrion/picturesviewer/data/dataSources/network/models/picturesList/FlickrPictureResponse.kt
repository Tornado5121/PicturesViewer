package com.playrion.picturesviewer.data.dataSources.network.models.picturesList

data class FlickrPictureResponse(
    val extra: Extra,
    val photos: Photos,
    val stat: String
)