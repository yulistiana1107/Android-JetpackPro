package com.yulistiana.jetpackpro.lastsubmission.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.yulistiana.jetpackpro.lastsubmission.LiveDataUtil
import com.yulistiana.jetpackpro.lastsubmission.PagedListTestUtil
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.LocalDataSource
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityMovies
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityTvShows
import com.yulistiana.jetpackpro.lastsubmission.data.source.remote.RemoteData
import com.yulistiana.jetpackpro.lastsubmission.utils.Data
import com.yulistiana.jetpackpro.lastsubmission.vo.Resources
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class CatalogRepositoriesTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteData::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val catalogRepository = FakeCatalogRepository(remote, local)

    private val listMovie = Data.dataMovie()
    private val listTvShow = Data.dataTvShows()
    private val movie = Data.dataMovie()[0]
    private val tvShow = Data.dataTvShows()[0]

    @Test
    fun getTopRatedMovies() {
        val dataSourceFactories = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, EntityMovies>
        `when`(local.getMoviesList()).thenReturn(dataSourceFactories)
        catalogRepository.getTopRatedMovies()

        val EntityMovies = Resources.success(PagedListTestUtil.mockPagedList(Data.dataMovie()))
        verify(local).getMoviesList()
        org.junit.Assert.assertNotNull(EntityMovies.data)
        assertEquals(listMovie.size.toLong(), EntityMovies.data?.size?.toLong())
    }

    @Test
    fun getTopRatedTvShows() {
        val dataSourceFactories = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, EntityTvShows>
        `when`(local.getTvShowsList()).thenReturn(dataSourceFactories)
        catalogRepository.getTopRatedTvShows()

        val tvShowEntity = Resources.success(PagedListTestUtil.mockPagedList(Data.dataTvShows()))
        verify(local).getTvShowsList()
        org.junit.Assert.assertNotNull(tvShowEntity.data)
        assertEquals(listTvShow.size.toLong(), tvShowEntity.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyMovies = MutableLiveData<EntityMovies>()
        dummyMovies.value = movie
        `when`(local.getMovieDetail(movie.movieId)).thenReturn(dummyMovies)

        val data = LiveDataUtil.getValue(catalogRepository.getMovieDetail(movie.movieId))
        verify(local).getMovieDetail(movie.movieId)
        org.junit.Assert.assertNotNull(data)
        assertEquals(movie.movieId, data.movieId)
    }

    @Test
    fun getTvShowDetail() {
        val dummyTvShows = MutableLiveData<EntityTvShows>()
        dummyTvShows.value = tvShow
        `when`(local.getTvShowDetail(tvShow.tvShowId)).thenReturn(dummyTvShows)

        val data = LiveDataUtil.getValue(catalogRepository.getTvShowDetail(tvShow.tvShowId))
        verify(local).getTvShowDetail(tvShow.tvShowId)
        org.junit.Assert.assertNotNull(data)
        assertEquals(tvShow.tvShowId, data.tvShowId)
    }

    @Test
    fun getFavoriteMoviesList() {
        val dataSourceFactories = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, EntityMovies>
        `when`(local.getFavoriteMoviesList()).thenReturn(dataSourceFactories)
        catalogRepository.getFavoriteMoviesList()

        val EntityMovies = Resources.success(PagedListTestUtil.mockPagedList(Data.dataMovie()))
        verify(local).getFavoriteMoviesList()
        org.junit.Assert.assertNotNull(EntityMovies.data)
        assertEquals(listMovie.size.toLong(), EntityMovies.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTvShowsList() {
        val dataSourceFactories = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, EntityTvShows>
        `when`(local.getFavoriteTvShowsList()).thenReturn(dataSourceFactories)
        catalogRepository.getFavoriteTvShowsList()

        val tvShowEntity = Resources.success(PagedListTestUtil.mockPagedList(Data.dataTvShows()))
        verify(local).getFavoriteTvShowsList()
        org.junit.Assert.assertNotNull(tvShowEntity.data)
        assertEquals(listTvShow.size.toLong(), tvShowEntity.data?.size?.toLong())
    }
}