package com.playrion.picturesviewer.data.dataSources.network.models.picturesList

data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<Photo>,
    val total: Int
)