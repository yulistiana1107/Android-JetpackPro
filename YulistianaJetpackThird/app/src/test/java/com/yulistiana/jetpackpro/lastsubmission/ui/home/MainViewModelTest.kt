package com.yulistiana.jetpackpro.lastsubmission.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.yulistiana.jetpackpro.lastsubmission.data.source.CatalogRepositories
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityMovies
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityTvShows
import com.yulistiana.jetpackpro.lastsubmission.vo.Resources
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepositories: CatalogRepositories

    @Mock
    private lateinit var observerMovies: Observer<Resources<PagedList<EntityMovies>>>

    @Mock
    private lateinit var observerTvShows: Observer<Resources<PagedList<EntityTvShows>>>

    @Mock
    private lateinit var moviesPagedList: PagedList<EntityMovies>

    @Mock
    private lateinit var tvShowsPagedList: PagedList<EntityTvShows>

    @Before
    fun setUp() {
        viewModel = MainViewModel(catalogRepositories)
    }

    @Test
    fun getListNowPlayingMovies() {
        val dummyMovie = Resources.success(moviesPagedList)
        Mockito.`when`(dummyMovie.data?.size).thenReturn(5)
        val movie = MutableLiveData<Resources<PagedList<EntityMovies>>>()
        movie.value = dummyMovie

        Mockito.`when`(catalogRepositories.getTopRatedMovies()).thenReturn(movie)
        val EntityMovies = viewModel.getListNowPlayingMovies().value?.data
        Mockito.verify(catalogRepositories).getTopRatedMovies()
        Assert.assertNotNull(EntityMovies)
        Assert.assertEquals(5, EntityMovies?.size)

        viewModel.getListNowPlayingMovies().observeForever(observerMovies)
        Mockito.verify(observerMovies).onChanged(dummyMovie)
    }

    @Test
    fun getListOnTheAirTvShows() {
        val dummyTvShows = Resources.success(tvShowsPagedList)
        Mockito.`when`(dummyTvShows.data?.size).thenReturn(5)
        val tvShows = MutableLiveData<Resources<PagedList<EntityTvShows>>>()
        tvShows.value = dummyTvShows

        Mockito.`when`(catalogRepositories.getTopRatedTvShows()).thenReturn(tvShows)
        val tvShowEntity = viewModel.getListOnTheAirTvShows().value?.data
        Mockito.verify(catalogRepositories).getTopRatedTvShows()
        Assert.assertNotNull(tvShowEntity)
        Assert.assertEquals(5, tvShowEntity?.size)

        viewModel.getListOnTheAirTvShows().observeForever(observerTvShows)
        Mockito.verify(observerTvShows).onChanged(dummyTvShows)
    }
}