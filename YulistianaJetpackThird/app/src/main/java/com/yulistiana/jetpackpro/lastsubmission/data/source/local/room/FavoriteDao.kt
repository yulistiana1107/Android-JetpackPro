package com.yulistiana.jetpackpro.lastsubmission.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityMovies
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityTvShows

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite_movie")
    fun getMoviesList() : DataSource.Factory<Int, EntityMovies>

    @Query("SELECT * FROM favorite_tvshow")
    fun getTvShowsList() : DataSource.Factory<Int, EntityTvShows>

    @Query("SELECT * FROM favorite_movie WHERE isFavorite = 1")
    fun getFavoriteMoviesList() : DataSource.Factory<Int, EntityMovies>

    @Query("SELECT * FROM favorite_tvshow WHERE isFavorite = 1")
    fun getFavoriteTvShowsList() : DataSource.Factory<Int, EntityTvShows>

    @Query("SELECT * FROM favorite_movie WHERE movieId = :movieId")
    fun getMovieDetailById(movieId: Int) : LiveData<EntityMovies>

    @Query("SELECT * FROM favorite_tvshow WHERE tvShowId = :tvShowId")
    fun getTvShowDetailById(tvShowId: Int) : LiveData<EntityTvShows>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = EntityMovies::class)
    fun insertMovies(movies: List<EntityMovies>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = EntityTvShows::class)
    fun insertTvShows(tvShows: List<EntityTvShows>)

    @Update(entity = EntityMovies::class)
    fun updateMovie(movie : EntityMovies)

    @Update(entity = EntityTvShows::class)
    fun updateTvShow(tvShows: EntityTvShows)

}