package com.playrion.picturesviewer.data.repositories

import com.playrion.picturesviewer.data.FlickrResult
import com.playrion.picturesviewer.data.dataSources.dataBase.DatabaseRepository
import com.playrion.picturesviewer.data.dataSources.dataBase.extensions.asDomain
import com.playrion.picturesviewer.data.dataSources.dataBase.extensions.asDomainList
import com.playrion.picturesviewer.data.dataSources.network.UrlFetcher
import com.playrion.picturesviewer.data.dataSources.network.extensions.asEntity
import com.playrion.picturesviewer.data.dataSources.network.extensions.asEntityList
import com.playrion.picturesviewer.domain.iRepository.FlickrPictureRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FlickrPictureRepositoryImpl(
    private val urlFetcher: UrlFetcher,
    private val databaseRepository: DatabaseRepository,
    private val coroutineScope: CoroutineScope,
) : FlickrPictureRepository {

    override suspend fun getNewPicturesPage(): FlickrResult {
        return try {
            val updatedList = urlFetcher.getPictureList().photos.photo.asEntityList()
            databaseRepository.insertFlickrList(updatedList)
            for (picture in updatedList) {
                coroutineScope.launch {
                    databaseRepository.insertPictureInfo(
                        urlFetcher.getPictureInfo(picture.remoteId).asEntity()
                    )
                }
            }
            FlickrResult.Success(updatedList.asDomainList())
        } catch (e: Exception) {
            FlickrResult.Error(e)
        }
    }

    override suspend fun clearDataBase() {
        databaseRepository.clearFlickrList()
    }

    override suspend fun getFlickrList(): FlickrResult {
        return try {
            FlickrResult.Success(databaseRepository.getFlickrList().asDomainList())
        } catch (e: Exception) {
            FlickrResult.Error(e)
        }
    }

    override suspend fun getPicturesSet(id: String): FlickrResult {
        return try {
            val currentItem = databaseRepository.getFlickrByFlickrId(id)
            val previousItem = databaseRepository.getFlickrById(currentItem.flickr.id - 1)
            val nextItem = databaseRepository.getFlickrById(currentItem.flickr.id + 1)
            when {
                previousItem == null -> {
                    FlickrResult.Success(
                        listOf(
                            currentItem.asDomain(),
                            nextItem?.asDomain(),
                        )
                    )
                }

                nextItem == null -> {
                    FlickrResult.Success(
                        listOf(
                            previousItem.asDomain(),
                            currentItem.asDomain(),
                        )
                    )
                }

                else -> {
                    FlickrResult.Success(
                        listOf(
                            previousItem.asDomain(),
                            currentItem.asDomain(),
                            nextItem.asDomain(),
                        )
                    )
                }
            }
        } catch (e: Exception) {
            FlickrResult.Error(e)
        }
    }
}