package com.yulistiana.jetpackpro.lastsubmission.di

import com.yulistiana.jetpackpro.lastsubmission.di.home.MainModule
import com.yulistiana.jetpackpro.lastsubmission.ui.detail.DetailKuyActivity
import com.yulistiana.jetpackpro.lastsubmission.ui.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailKuyActivity(): DetailKuyActivity

}