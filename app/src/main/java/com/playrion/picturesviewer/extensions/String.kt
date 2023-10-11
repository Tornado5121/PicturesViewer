package com.playrion.picturesviewer.extensions

import java.time.Instant
import java.time.ZoneId

fun String.asDataTime(): String {
    val instant = Instant.ofEpochSecond(this.toLong())
    val zoneId = ZoneId.of("UTC")
    return instant.atZone(zoneId).toLocalDateTime().toString()
}