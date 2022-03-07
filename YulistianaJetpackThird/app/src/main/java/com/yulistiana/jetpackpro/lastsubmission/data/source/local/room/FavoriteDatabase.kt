package com.yulistiana.jetpackpro.lastsubmission.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityMovies
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityTvShows

@Database(entities = [EntityMovies::class, EntityTvShows::class], version = 4, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {

        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        fun getInstance(context: Context): FavoriteDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDatabase::class.java,
                    "catalog.db"
                        ).fallbackToDestructiveMigration() .build()
            }
    }
}