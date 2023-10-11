package com.playrion.picturesviewer.data.dataSources.network.extensions

import com.playrion.picturesviewer.data.dataSources.dataBase.room.entity.PicturesEntity
import com.playrion.picturesviewer.data.dataSources.network.models.picturesList.Photo

fun Photo.asEntity() =
    PicturesEntity(
        remoteId = this.id,
        title = this.title,
        thumbnailUrl = "https://live.staticflickr.com/${this.server}/${this.id}_${this.secret}_q.jpg",
        pictureUrl = "https://live.staticflickr.com/${this.server}/${this.id}_${this.secret}_b.jpg",
    )

fun List<Photo>.asEntityList() = map {
    it.asEntity()
}