package com.playrion.picturesviewer.data.dataSources.dataBase.room

import androidx.room.Embedded
import androidx.room.Relation
import com.playrion.picturesviewer.data.dataSources.dataBase.room.entity.PicturesEntity
import com.playrion.picturesviewer.data.dataSources.dataBase.room.entity.PicturesInfoEntity

data class PictureAndInfo(
    @Embedded val flickr: PicturesEntity,
    @Relation(
        parentColumn = "remoteId",
        entityColumn = "flickrId"
    )
    val info: PicturesInfoEntity
)
