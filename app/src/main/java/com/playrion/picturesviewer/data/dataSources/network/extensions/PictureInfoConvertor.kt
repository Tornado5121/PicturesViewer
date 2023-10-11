package com.playrion.picturesviewer.data.dataSources.network.extensions

import com.playrion.picturesviewer.data.dataSources.dataBase.room.entity.PicturesInfoEntity
import com.playrion.picturesviewer.data.dataSources.network.models.picturesInfo.PicturesInfo

fun PicturesInfo.asEntity() =
    PicturesInfoEntity(
        flickrId = this.photo.id,
        description = this.photo.description._content,
        dateUploaded = this.photo.dateuploaded
    )