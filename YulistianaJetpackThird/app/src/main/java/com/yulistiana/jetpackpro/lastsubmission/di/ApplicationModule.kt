package com.yulistiana.jetpackpro.lastsubmission.di

import android.app.Application
import com.yulistiana.jetpackpro.lastsubmission.data.source.CatalogRepositories
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.LocalDataSource
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.room.FavoriteDao
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.room.FavoriteDatabase
import com.yulistiana.jetpackpro.lastsubmission.data.source.remote.RemoteData
import com.yulistiana.jetpackpro.lastsubmission.data.source.remote.api.RetrofitService
import com.yulistiana.jetpackpro.lastsubmission.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule {

    companion object {

        @Singleton
        @Provides
        fun provideFavoriteDatabase(application: Application): FavoriteDatabase =
            FavoriteDatabase.getInstance(application)

        @Singleton
        @Provides
        fun provideFavoriteDao(favoriteDatabase: FavoriteDatabase): FavoriteDao =
            favoriteDatabase.favoriteDao()

        @Singleton
        @Provides
        fun provideLocalDataSource(favoriteDao: FavoriteDao): LocalDataSource =
            LocalDataSource(favoriteDao)

        @Singleton
        @Provides
        fun provideRemoteData(retrofitService: RetrofitService): RemoteData =
            RemoteData(retrofitService)

        @Singleton
        @Provides
        fun provideCatalogRepositories(
            remoteData: RemoteData,
            localDataSource: LocalDataSource
        ): CatalogRepositories = CatalogRepositories(remoteData, localDataSource)

        @Singleton
        @Provides
        fun provideViewModelFactory(catalogRepositories: CatalogRepositories): ViewModelFactory =
            ViewModelFactory(catalogRepositories)

    }
}