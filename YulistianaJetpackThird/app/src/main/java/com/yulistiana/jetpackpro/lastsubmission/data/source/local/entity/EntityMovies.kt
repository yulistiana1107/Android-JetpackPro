package com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "favorite_movie")
@Parcelize
data class EntityMovies(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int = 0,

    @ColumnInfo(name = "movieTitle")
    var name: String? = null,

    @ColumnInfo(name = "movieDetail")
    var desc: String? = null,

    @ColumnInfo(name = "moviePoster")
    var poster: String? = null,

    @ColumnInfo(name = "movieBackground")
    var imgBackground: String? = null,

    @ColumnInfo(name = "movieRate")
    var rate: Int? = null,

    @NonNull
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
): Parcelable