package com.yulistiana.jetpackpro.lastsubmission.ui.home.content.movie

import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityMovies

interface MovieClicked {
    fun onItemClicked(data: EntityMovies)
}