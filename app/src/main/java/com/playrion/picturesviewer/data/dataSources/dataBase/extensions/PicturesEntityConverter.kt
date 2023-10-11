package com.playrion.picturesviewer.data.dataSources.dataBase.extensions

import com.playrion.picturesviewer.data.dataSources.dataBase.room.entity.PicturesEntity
import com.playrion.picturesviewer.domain.models.FlickrPicture

fun PicturesEntity.asDomain() =
    FlickrPicture(
        id = this.remoteId,
        title = this.title,
        thumbnailUrl = this.thumbnailUrl,
        imageUrl = this.pictureUrl
    )

fun List<PicturesEntity>.asDomainList() = map {
    it.asDomain()
}