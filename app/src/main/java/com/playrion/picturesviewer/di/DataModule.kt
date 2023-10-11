package com.playrion.picturesviewer.di

import com.playrion.picturesviewer.BuildConfig
import com.playrion.picturesviewer.data.dataSources.dataBase.DatabaseRepository
import com.playrion.picturesviewer.data.dataSources.dataBase.DatabaseRepositoryImpl
import com.playrion.picturesviewer.data.dataSources.dataBase.room.FlickrDatabase
import com.playrion.picturesviewer.data.dataSources.network.PicturesApi
import com.playrion.picturesviewer.data.dataSources.network.UrlFetcher
import com.playrion.picturesviewer.data.dataSources.network.UrlFetcherImpl
import com.playrion.picturesviewer.data.repositories.FlickrPictureRepositoryImpl
import com.playrion.picturesviewer.domain.iRepository.FlickrPictureRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    factory<UrlFetcher> { UrlFetcherImpl(get()) }
    factory<DatabaseRepository> { DatabaseRepositoryImpl(get()) }
    factory<FlickrPictureRepository> { FlickrPictureRepositoryImpl(get(), get(), get()) }
    single { FlickrDatabase.getInstance(androidContext()).flickrDao() }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_BACKEND_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(PicturesApi::class.java)
    }
    factory { CoroutineScope(SupervisorJob() + Dispatchers.IO) }
}