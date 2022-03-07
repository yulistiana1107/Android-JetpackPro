package com.yulistiana.jetpackpro.lastsubmission.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.yulistiana.jetpackpro.lastsubmission.data.source.CatalogRepositories
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityMovies
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityTvShows
import com.yulistiana.jetpackpro.lastsubmission.utils.Data
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private val dummyMovies = Data.dataMovie()[0]
    private val movieId = dummyMovies.movieId
    private val dummyTvShows = Data.dataTvShows()[0]
    private val tvShowId = dummyTvShows.tvShowId

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepositories: CatalogRepositories

    @Mock
    private lateinit var observerMovie: Observer<EntityMovies>

    @Mock
    private lateinit var observerTvShow: Observer<EntityTvShows>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(catalogRepositories)
    }

    @Test
    fun getMovieDetail() {
        val movieDummy = MutableLiveData<EntityMovies>()
        movieDummy.value = dummyMovies

        Mockito.`when`(catalogRepositories.getMovieDetail(movieId)).thenReturn(movieDummy)

        val moviesData = viewModel.getMovieDetail(movieId).value

        Assert.assertNotNull(moviesData)
        assertEquals(dummyMovies.id, moviesData?.id)
        assertEquals(dummyMovies.movieId, moviesData?.movieId)
        assertEquals(dummyMovies.name, moviesData?.name)
        assertEquals(dummyMovies.desc, moviesData?.desc)
        assertEquals(dummyMovies.poster, moviesData?.poster)
        assertEquals(dummyMovies.imgBackground, moviesData?.imgBackground)

        viewModel.getMovieDetail(movieId).observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovies)

    }

    @Test
    fun getTvShowDetail() {
        val tvShowsDummy = MutableLiveData<EntityTvShows>()
        tvShowsDummy.value = dummyTvShows

        Mockito.`when`(catalogRepositories.getTvShowDetail(tvShowId)).thenReturn(tvShowsDummy)

        val tvShowsData = viewModel.getTvShowDetail(tvShowId).value

        Assert.assertNotNull(tvShowsData)
        assertEquals(dummyTvShows.id, tvShowsData?.id)
        assertEquals(dummyTvShows.tvShowId, tvShowsData?.tvShowId)
        assertEquals(dummyTvShows.name, tvShowsData?.name)
        assertEquals(dummyTvShows.desc, tvShowsData?.desc)
        assertEquals(dummyTvShows.poster, tvShowsData?.poster)
        assertEquals(dummyTvShows.imgBackground, tvShowsData?.imgBackground)

        viewModel.getTvShowDetail(tvShowId).observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyTvShows)
    }
}