package com.playrion.picturesviewer.data.dataSources.network.models.picturesInfo

data class Gift(
    val eligible_durations: List<String>,
    val gift_eligible: Boolean,
    val new_flow: Boolean
)