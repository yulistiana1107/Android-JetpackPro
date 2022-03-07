package com.yulistiana.jetpackpro.lastsubmission.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.yulistiana.jetpackpro.lastsubmission.data.source.CatalogRepositories
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityMovies
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityTvShows
import com.yulistiana.jetpackpro.lastsubmission.vo.Resources
import javax.inject.Inject

class MainViewModel @Inject constructor(private val catalogRepositories: CatalogRepositories) :
    ViewModel() {

    fun getListNowPlayingMovies(): LiveData<Resources<PagedList<EntityMovies>>> = catalogRepositories.getTopRatedMovies()

    fun getListOnTheAirTvShows(): LiveData<Resources<PagedList<EntityTvShows>>> = catalogRepositories.getTopRatedTvShows()

}