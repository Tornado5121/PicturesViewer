package com.playrion.picturesviewer.data.dataSources.dataBase.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PicturesEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val remoteId: String,
    val title: String,
    val thumbnailUrl: String,
    val pictureUrl: String
)