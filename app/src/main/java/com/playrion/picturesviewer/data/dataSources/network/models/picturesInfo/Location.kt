package com.playrion.picturesviewer.data.dataSources.network.models.picturesInfo

data class Location(
    val accuracy: String,
    val context: String,
    val country: Country,
    val county: County,
    val latitude: String,
    val locality: Locality,
    val longitude: String,
    val neighbourhood: Neighbourhood,
    val region: Region
)