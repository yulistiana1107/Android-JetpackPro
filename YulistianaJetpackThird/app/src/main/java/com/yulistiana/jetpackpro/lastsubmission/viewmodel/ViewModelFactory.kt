package com.yulistiana.jetpackpro.lastsubmission.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yulistiana.jetpackpro.lastsubmission.data.source.CatalogRepositories
import com.yulistiana.jetpackpro.lastsubmission.ui.detail.DetailViewModel
import com.yulistiana.jetpackpro.lastsubmission.ui.home.MainViewModel
import com.yulistiana.jetpackpro.lastsubmission.ui.home.content.favorite.FavoriteViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val mCatalogRepositories: CatalogRepositories): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(mCatalogRepositories) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mCatalogRepositories) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mCatalogRepositories) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}