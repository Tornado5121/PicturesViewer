package com.playrion.picturesviewer.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class FlickrPicture(
    val id: String = "",
    val title: String = "",
    val thumbnailUrl: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val uploadDate: String = ""
) : Parcelable