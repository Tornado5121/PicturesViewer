package com.playrion.picturesviewer.data.dataSources.network

import com.playrion.picturesviewer.BuildConfig
import com.playrion.picturesviewer.data.dataSources.network.models.picturesInfo.PicturesInfo
import com.playrion.picturesviewer.data.dataSources.network.models.picturesList.FlickrPictureResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UrlFetcherImpl(
    private val api: PicturesApi
) : UrlFetcher {

    private val format = "json"
    private val noJsonCallBack = "1"
    private val dayBeforeToday = 2L
    private val dayFormat = "yyyy-MM-dd"
    private var requestedPage = "0"
    private var itemsPrePage = 20

    private val getList = "flickr.interestingness.getList"
    private val getInfo = "flickr.photos.getInfo"

    override suspend fun getPictureList(): FlickrPictureResponse =
        withContext(Dispatchers.IO) {
            requestedPage = (requestedPage.toInt() + 1).toString()
            api.getPictures(
                getList,
                BuildConfig.API_KEY,
                format,
                noJsonCallBack,
                requestedPage,
                LocalDateTime.now().minusDays(dayBeforeToday)
                    .format(DateTimeFormatter.ofPattern(dayFormat)),
                itemsPrePage
            )
        }

    override suspend fun getPictureInfo(id: String): PicturesInfo =
        withContext(Dispatchers.IO) {
            api.getPictureInfo(
                getInfo,
                BuildConfig.API_KEY,
                format,
                noJsonCallBack,
                id
            )
        }
}