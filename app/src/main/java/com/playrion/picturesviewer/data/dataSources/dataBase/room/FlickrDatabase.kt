package com.playrion.picturesviewer.data.dataSources.dataBase.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.playrion.picturesviewer.data.dataSources.dataBase.room.entity.PicturesEntity
import com.playrion.picturesviewer.data.dataSources.dataBase.room.entity.PicturesInfoEntity

@Database(
    entities = [PicturesEntity::class, PicturesInfoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FlickrDatabase : RoomDatabase() {

    abstract fun flickrDao(): FlickrDao

    companion object {
        @Volatile
        private var INSTANCE: FlickrDatabase? = null

        fun getInstance(context: Context): FlickrDatabase {
            val currentInstance = INSTANCE
            if (currentInstance != null) {
                return currentInstance
            }

            synchronized(this) {
                val dataBaseInstance = INSTANCE
                return if (dataBaseInstance != null) {
                    dataBaseInstance
                } else {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        FlickrDatabase::class.java,
                        "user_database"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }
}