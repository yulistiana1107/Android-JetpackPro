package com.yulistiana.jetpackpro.lastsubmission.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.LocalDataSource
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityMovies
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityTvShows
import com.yulistiana.jetpackpro.lastsubmission.data.source.remote.RemoteData
import com.yulistiana.jetpackpro.lastsubmission.data.source.remote.response.MovieResponses
import com.yulistiana.jetpackpro.lastsubmission.data.source.remote.response.TvShowResponses
import com.yulistiana.jetpackpro.lastsubmission.data.source.remote.vo.ApiResponses
import com.yulistiana.jetpackpro.lastsubmission.vo.Resources
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject


class CatalogRepositories @Inject constructor(
    private val remoteData: RemoteData,
    private val localDataSource: LocalDataSource
) : CatalogResources {

    override fun getTopRatedMovies(): LiveData<Resources<PagedList<EntityMovies>>> {
        return object : NetworkBoundResource<PagedList<EntityMovies>, List<MovieResponses>>() {
            public override fun loadFromDB(): LiveData<PagedList<EntityMovies>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getMoviesList(), config).build()
            }

            override fun shouldFetch(data: PagedList<EntityMovies>?): Boolean =
                data == null || data.isEmpty()


            public override fun createCall(): LiveData<ApiResponses<List<MovieResponses>>> =
                remoteData.getTopRatedMovies()

            public override fun saveCallResult(data: List<MovieResponses>) {
                val movieList = ArrayList<EntityMovies>()
                for (items in data) {
                    val movie = EntityMovies(
                        null,
                        items.id,
                        items.name,
                        items.desc,
                        items.poster,
                        items.imgPreview,
                        items.rate,
                        false
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getFavoriteMoviesList(): LiveData<PagedList<EntityMovies>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteMoviesList(), config).build()
    }

    override fun getMovieDetail(movieId: Int): LiveData<EntityMovies> =
        localDataSource.getMovieDetail(movieId)


    override fun getTopRatedTvShows(): LiveData<Resources<PagedList<EntityTvShows>>> {
        return object : NetworkBoundResource<PagedList<EntityTvShows>, List<TvShowResponses>>() {
            public override fun loadFromDB(): LiveData<PagedList<EntityTvShows>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getTvShowsList(), config).build()
            }

            override fun shouldFetch(data: PagedList<EntityTvShows>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponses<List<TvShowResponses>>> =
                remoteData.getTopRatedTvShows()


            public override fun saveCallResult(data: List<TvShowResponses>) {
                val tvShowList = ArrayList<EntityTvShows>()
                for (items in data) {
                    val tvShow = EntityTvShows(
                        null,
                        items.id,
                        items.name,
                        items.desc,
                        items.poster,
                        items.imgPreview,
                        items.rate,
                        false
                    )
                    tvShowList.add(tvShow)
                }

                localDataSource.insertTvShows(tvShowList)
            }

        }.asLiveData()
    }

    override fun getFavoriteTvShowsList(): LiveData<PagedList<EntityTvShows>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShowsList(), config).build()
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<EntityTvShows> =
        localDataSource.getTvShowDetail(tvShowId)

    override fun setFavoriteMovies(movie: EntityMovies) {
        CoroutineScope(IO).launch {
            localDataSource.setFavoriteMovies(movie)
        }
    }

    override fun setFavoriteTvShows(tvShow: EntityTvShows) {
        CoroutineScope(IO).launch {
            localDataSource.setFavoriteTvShows(tvShow)
        }
    }


}