package com.yulistiana.jetpackpro.lastsubmission.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityMovies
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityTvShows
import com.yulistiana.jetpackpro.lastsubmission.vo.Resources

interface CatalogResources {

    fun getTopRatedMovies(): LiveData<Resources<PagedList<EntityMovies>>>

    fun getFavoriteMoviesList(): LiveData<PagedList<EntityMovies>>

    fun getMovieDetail(movieId: Int): LiveData<EntityMovies>

    fun getTopRatedTvShows(): LiveData<Resources<PagedList<EntityTvShows>>>

    fun getFavoriteTvShowsList(): LiveData<PagedList<EntityTvShows>>

    fun getTvShowDetail(tvShowId: Int): LiveData<EntityTvShows>

    fun setFavoriteMovies(movie: EntityMovies)

    fun setFavoriteTvShows(tvShow: EntityTvShows)

}