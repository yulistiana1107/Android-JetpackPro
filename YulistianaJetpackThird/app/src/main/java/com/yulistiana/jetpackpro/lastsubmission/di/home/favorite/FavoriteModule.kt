package com.yulistiana.jetpackpro.lastsubmission.di.home.favorite

import com.yulistiana.jetpackpro.lastsubmission.ui.home.content.favorite.movie.FavoriteMovieFragment
import com.yulistiana.jetpackpro.lastsubmission.ui.home.content.favorite.tvshow.FavoriteTvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteModule {

    @ContributesAndroidInjector
    abstract fun contributeFavoriteMoviesFragment() : FavoriteMovieFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteTvShowsFragment() : FavoriteTvShowFragment
}