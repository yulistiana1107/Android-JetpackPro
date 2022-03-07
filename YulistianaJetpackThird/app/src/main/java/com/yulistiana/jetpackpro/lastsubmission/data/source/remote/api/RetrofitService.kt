package com.yulistiana.jetpackpro.lastsubmission.data.source.remote.api

import com.yulistiana.jetpackpro.lastsubmission.BuildConfig
import com.yulistiana.jetpackpro.lastsubmission.data.source.remote.response.Responses
import com.yulistiana.jetpackpro.lastsubmission.data.source.remote.response.MovieResponses
import com.yulistiana.jetpackpro.lastsubmission.data.source.remote.response.TvShowResponses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API
    ) : Call<Responses<MovieResponses>>

    @GET("tv/top_rated")
    fun getTopRatedTvShow(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API
    ) : Call<Responses<TvShowResponses>>

}