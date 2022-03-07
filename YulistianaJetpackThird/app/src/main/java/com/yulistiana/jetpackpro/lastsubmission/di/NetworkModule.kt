package com.yulistiana.jetpackpro.lastsubmission.di

import com.yulistiana.jetpackpro.lastsubmission.BuildConfig
import com.yulistiana.jetpackpro.lastsubmission.data.source.remote.api.RetrofitService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {

        @Singleton
        @Provides
        fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }.build()

        @Singleton
        @Provides
        fun provideApiRetrofitInstance(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
            baseUrl(BuildConfig.TMDB_URL)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
        }.build()

        @Provides
        fun provideKuyApi(retrofit: Retrofit): RetrofitService = retrofit.create(RetrofitService::class.java)

    }

}