package com.yulistiana.jetpackpro.lastsubmission.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yulistiana.jetpackpro.lastsubmission.data.source.CatalogRepositories
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityMovies
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityTvShows
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val catalogRepositories: CatalogRepositories) :
    ViewModel() {

    fun getMovieDetail(movieId: Int): LiveData<EntityMovies> =
        catalogRepositories.getMovieDetail(movieId)

    fun getTvShowDetail(tvShowId: Int): LiveData<EntityTvShows> =
        catalogRepositories.getTvShowDetail(tvShowId)

    fun setFavoriteMovies(movie: EntityMovies){
        catalogRepositories.setFavoriteMovies(movie)
    }

    fun setFavoriteTvShows(tvShow: EntityTvShows){
        catalogRepositories.setFavoriteTvShows(tvShow)
    }
}