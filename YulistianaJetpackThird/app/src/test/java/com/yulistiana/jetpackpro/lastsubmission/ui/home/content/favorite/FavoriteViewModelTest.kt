package com.yulistiana.jetpackpro.lastsubmission.ui.home.content.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.yulistiana.jetpackpro.lastsubmission.data.source.CatalogRepositories
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityMovies
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityTvShows
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepositories: CatalogRepositories

    @Mock
    private lateinit var observerMovies: Observer<PagedList<EntityMovies>>

    @Mock
    private lateinit var observerTvShows: Observer<PagedList<EntityTvShows>>

    @Mock
    private lateinit var moviesPagedList: PagedList<EntityMovies>

    @Mock
    private lateinit var tvShowsPagedList: PagedList<EntityTvShows>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(catalogRepositories)
    }

    @Test
    fun getListFavoriteMovie() {

        val dummyMovies = moviesPagedList
        Mockito.`when`(dummyMovies.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<EntityMovies>>()
        movie.value = dummyMovies

        Mockito.`when`(catalogRepositories.getFavoriteMoviesList()).thenReturn(movie)
        val EntityMovies = viewModel.getListFavoriteMovie().value
        Mockito.verify(catalogRepositories).getFavoriteMoviesList()
        Assert.assertNotNull(EntityMovies)
        Assert.assertEquals(5, EntityMovies?.size)

        viewModel.getListFavoriteMovie().observeForever(observerMovies)
        Mockito.verify(observerMovies).onChanged(dummyMovies)

    }

    @Test
    fun getListFavoriteTvShow() {
        val dummyTvShows = tvShowsPagedList
        Mockito.`when`(dummyTvShows.size).thenReturn(5)
        val tvShows = MutableLiveData<PagedList<EntityTvShows>>()
        tvShows.value = dummyTvShows

        Mockito.`when`(catalogRepositories.getFavoriteTvShowsList()).thenReturn(tvShows)
        val tvShowEntity = viewModel.getListFavoriteTvShow().value
        Mockito.verify(catalogRepositories).getFavoriteTvShowsList()
        Assert.assertNotNull(tvShowEntity)
        Assert.assertEquals(5, tvShowEntity?.size)

        viewModel.getListFavoriteTvShow().observeForever(observerTvShows)
        Mockito.verify(observerTvShows).onChanged(dummyTvShows)
    }
}
