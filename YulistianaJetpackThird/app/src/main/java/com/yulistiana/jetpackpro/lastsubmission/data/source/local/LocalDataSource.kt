package com.yulistiana.jetpackpro.lastsubmission.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityMovies
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityTvShows
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.room.FavoriteDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val favoriteDao: FavoriteDao) {

    fun getMoviesList() : DataSource.Factory<Int, EntityMovies> = favoriteDao.getMoviesList()

    fun getFavoriteMoviesList() : DataSource.Factory<Int, EntityMovies> = favoriteDao.getFavoriteMoviesList()

    fun getTvShowsList() : DataSource.Factory<Int, EntityTvShows> = favoriteDao.getTvShowsList()

    fun getFavoriteTvShowsList() : DataSource.Factory<Int, EntityTvShows> = favoriteDao.getFavoriteTvShowsList()

    fun getMovieDetail(movieId: Int) : LiveData<EntityMovies> = favoriteDao.getMovieDetailById(movieId)

    fun getTvShowDetail(tvShowId: Int) : LiveData<EntityTvShows> = favoriteDao.getTvShowDetailById(tvShowId)

    fun insertMovies(movies: List<EntityMovies>) = favoriteDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<EntityTvShows>) = favoriteDao.insertTvShows(tvShows)

    fun setFavoriteMovies(movie : EntityMovies) {
        movie.isFavorite = !movie.isFavorite
        favoriteDao.updateMovie(movie)
    }

    fun setFavoriteTvShows(tvShow : EntityTvShows) {
        tvShow.isFavorite = !tvShow.isFavorite
        favoriteDao.updateTvShow(tvShow)
    }
}