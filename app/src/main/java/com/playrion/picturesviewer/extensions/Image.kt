package com.playrion.picturesviewer.extensions

import android.annotation.SuppressLint
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.playrion.picturesviewer.R

@SuppressLint("CheckResult")
fun ImageView.loadImageWithPlaceHolder(imageUrl: String) {
    Glide.with(this).load(imageUrl).placeholder(R.drawable.ic_launcher_background)
        .into(this)
}