package com.yulistiana.jetpackpro.lastsubmission.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponses(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("overview")
    var desc: String? = null,
    @SerializedName("poster_path")
    var poster: String? = null,
    @SerializedName("backdrop_path")
    var imgPreview: String? = null,
    @SerializedName("vote_count")
    var rate:Int? = 0
)