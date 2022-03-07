package com.yulistiana.jetpackpro.lastsubmission.ui.home.content.tvshow

import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityTvShows

interface TvShowClicked {
    fun onItemClicked(data: EntityTvShows)
}