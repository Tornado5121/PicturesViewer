package com.playrion.picturesviewer.data.dataSources.dataBase

import com.playrion.picturesviewer.data.dataSources.dataBase.room.FlickrDao
import com.playrion.picturesviewer.data.dataSources.dataBase.room.PictureAndInfo
import com.playrion.picturesviewer.data.dataSources.dataBase.room.entity.PicturesEntity
import com.playrion.picturesviewer.data.dataSources.dataBase.room.entity.PicturesInfoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseRepositoryImpl(
    private val flickrDao: FlickrDao
) : DatabaseRepository {

    override suspend fun getFlickrList(): List<PicturesEntity> =
        withContext(Dispatchers.IO) {
            flickrDao.getAllFlickrPictures()
        }

    override suspend fun insertFlickrList(list: List<PicturesEntity>) {
        withContext(Dispatchers.IO) {
            flickrDao.insertPictures(list)
        }
    }

    override suspend fun clearFlickrList() {
        withContext(Dispatchers.IO) {
            flickrDao.clearAllPictures()
        }
    }

    override suspend fun getFlickrById(id: Int): PictureAndInfo =
        withContext(Dispatchers.IO) {
            flickrDao.getFlickrById(id)
        }

    override suspend fun getFlickrByFlickrId(flickrId: String): PictureAndInfo =
        withContext(Dispatchers.IO) {
            flickrDao.getFlickrByFlickrId(flickrId)
        }

    override suspend fun insertPictureInfo(info: PicturesInfoEntity) {
        withContext(Dispatchers.IO) {
            flickrDao.insertPictureInfo(info)
        }
    }
}