package com.playrion.picturesviewer.data.dataSources.network

import com.playrion.picturesviewer.data.dataSources.network.models.picturesInfo.PicturesInfo
import com.playrion.picturesviewer.data.dataSources.network.models.picturesList.FlickrPictureResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PicturesApi {

    @GET("services/rest")
    suspend fun getPictures(
        @Query("method") method: String,
        @Query("api_key") apiKey: String,
        @Query("format") format: String,
        @Query("nojsoncallback") nojsoncallback: String,
        @Query("page") page: String,
        @Query("date") date: String,
        @Query("per_page") per_page: Int,
    ): FlickrPictureResponse

    @GET("services/rest")
    suspend fun getPictureInfo(
        @Query("method") method: String,
        @Query("api_key") apiKey: String,
        @Query("format") format: String,
        @Query("nojsoncallback") nojsoncallback: String,
        @Query("photo_id") photoId: String,
    ): PicturesInfo
}