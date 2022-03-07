package com.yulistiana.jetpackpro.lastsubmission.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yulistiana.jetpackpro.lastsubmission.data.source.remote.api.RetrofitService
import com.yulistiana.jetpackpro.lastsubmission.data.source.remote.response.MovieResponses
import com.yulistiana.jetpackpro.lastsubmission.data.source.remote.response.TvShowResponses
import com.yulistiana.jetpackpro.lastsubmission.data.source.remote.vo.ApiResponses
import com.yulistiana.jetpackpro.lastsubmission.utils.IdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.await
import javax.inject.Inject

class RemoteData @Inject constructor(private val catalogRetrofitService: RetrofitService) {

    fun getTopRatedMovies(): LiveData<ApiResponses<List<MovieResponses>>> {
        IdlingResource.increment()
        val resultMovieResponses = MutableLiveData<ApiResponses<List<MovieResponses>>>()
        CoroutineScope(IO).launch {
            try {
                val revenge = catalogRetrofitService.getTopRatedMovies().await()
                resultMovieResponses.postValue(ApiResponses.success(revenge.result!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultMovieResponses.postValue(
                    ApiResponses.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        IdlingResource.decrement()
        return resultMovieResponses
    }

    fun getTopRatedTvShows(): LiveData<ApiResponses<List<TvShowResponses>>> {
        IdlingResource.increment()
        val resultTvShowResponses = MutableLiveData<ApiResponses<List<TvShowResponses>>>()
        CoroutineScope(IO).launch {
            try {
                val revenge = catalogRetrofitService.getTopRatedTvShow().await()
                resultTvShowResponses.postValue(ApiResponses.success(revenge.result!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultTvShowResponses.postValue(
                    ApiResponses.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        IdlingResource.decrement()
        return resultTvShowResponses
    }

}