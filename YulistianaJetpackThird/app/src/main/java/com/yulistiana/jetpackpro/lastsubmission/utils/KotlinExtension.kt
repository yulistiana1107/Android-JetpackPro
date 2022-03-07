package com.yulistiana.jetpackpro.lastsubmission.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadFromUrl(path: String) {
    Glide.with(this).clear(this)
    Glide.with(this)
        .setDefaultRequestOptions(RequestOptions()
    ).load(path).into(this)
}