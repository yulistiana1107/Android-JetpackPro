package com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "favorite_tvshow")
@Parcelize
data class EntityTvShows(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @NonNull
    @ColumnInfo(name = "tvShowId")
    var tvShowId: Int = 0,

    @ColumnInfo(name = "tvShowTitle")
    var name: String? = null,

    @ColumnInfo(name = "tvShowDetail")
    var desc: String? = null,

    @ColumnInfo(name = "tvShowPoster")
    var poster: String? = null,

    @ColumnInfo(name = "tvShowBackground")
    var imgBackground: String? = null,

    @ColumnInfo(name = "tvShowRate")
    var rate: Int? = 0,

    @NonNull
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
): Parcelable