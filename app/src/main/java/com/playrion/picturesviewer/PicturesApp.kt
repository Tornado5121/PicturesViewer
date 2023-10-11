package com.playrion.picturesviewer

import android.app.Application
import com.playrion.picturesviewer.di.dataModule
import com.playrion.picturesviewer.di.featuresModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PicturesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PicturesApp)
            modules(listOf(featuresModule, dataModule))
        }
    }
}