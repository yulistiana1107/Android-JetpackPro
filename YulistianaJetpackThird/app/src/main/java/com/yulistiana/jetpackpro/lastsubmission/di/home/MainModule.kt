package com.yulistiana.jetpackpro.lastsubmission.di.home

import com.yulistiana.jetpackpro.lastsubmission.di.home.favorite.FavoriteModule
import com.yulistiana.jetpackpro.lastsubmission.ui.home.content.favorite.FavoriteFragment
import com.yulistiana.jetpackpro.lastsubmission.ui.home.content.movie.MovieFragment
import com.yulistiana.jetpackpro.lastsubmission.ui.home.content.tvshow.TvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun contributeMoviesFragment() : MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowsFragment() : TvShowFragment

    @ContributesAndroidInjector(modules = [FavoriteModule::class])
    abstract fun contributeFavoritesFragment() : FavoriteFragment
}