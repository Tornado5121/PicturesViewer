package com.playrion.picturesviewer.data.dataSources.network.models.picturesInfo

data class Owner(
    val gift: Gift,
    val iconfarm: Int,
    val iconserver: String,
    val location: String,
    val nsid: String,
    val path_alias: String,
    val realname: String,
    val username: String
)