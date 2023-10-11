package com.playrion.picturesviewer.data.dataSources.dataBase.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.playrion.picturesviewer.data.dataSources.dataBase.room.entity.PicturesEntity
import com.playrion.picturesviewer.data.dataSources.dataBase.room.entity.PicturesInfoEntity

@Dao
interface FlickrDao {

    @Insert
    fun insertPictures(list: List<PicturesEntity>)

    @Insert
    fun insertPictureInfo(info: PicturesInfoEntity)

    @Query("DELETE FROM PicturesEntity")
    fun clearAllPictures()

    @Query("SELECT * FROM PicturesEntity where id = :innerId")
    fun getFlickrById(innerId: Int): PictureAndInfo

    @Query("SELECT * FROM PicturesEntity where remoteId = :flickrId")
    fun getFlickrByFlickrId(flickrId: String): PictureAndInfo

    @Query("SELECT * FROM PicturesEntity")
    fun getAllFlickrPictures(): List<PicturesEntity>
}