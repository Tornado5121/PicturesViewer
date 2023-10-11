package com.playrion.picturesviewer.data.dataSources.dataBase.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PicturesInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val flickrId: String,
    val description: String,
    val dateUploaded: String,
)
