package com.yulistiana.jetpackpro.lastsubmission.ui.home.content.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.yulistiana.jetpackpro.lastsubmission.data.source.CatalogRepositories
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityMovies
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityTvShows
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val catalogRepositories: CatalogRepositories) : ViewModel() {

    fun getListFavoriteMovie(): LiveData<PagedList<EntityMovies>> = catalogRepositories.getFavoriteMoviesList()

    fun getListFavoriteTvShow(): LiveData<PagedList<EntityTvShows>> = catalogRepositories.getFavoriteTvShowsList()
}