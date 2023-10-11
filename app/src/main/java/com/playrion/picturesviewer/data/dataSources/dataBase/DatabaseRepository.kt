package com.playrion.picturesviewer.data.dataSources.dataBase

import com.playrion.picturesviewer.data.dataSources.dataBase.room.PictureAndInfo
import com.playrion.picturesviewer.data.dataSources.dataBase.room.entity.PicturesEntity
import com.playrion.picturesviewer.data.dataSources.dataBase.room.entity.PicturesInfoEntity

interface DatabaseRepository {

    suspend fun getFlickrList(): List<PicturesEntity>
    suspend fun insertFlickrList(list: List<PicturesEntity>)
    suspend fun clearFlickrList()
    suspend fun getFlickrById(id: Int): PictureAndInfo?
    suspend fun getFlickrByFlickrId(flickrId: String): PictureAndInfo
    suspend fun insertPictureInfo(info: PicturesInfoEntity)
}