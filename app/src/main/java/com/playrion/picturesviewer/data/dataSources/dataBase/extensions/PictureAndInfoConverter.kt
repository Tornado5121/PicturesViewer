package com.playrion.picturesviewer.data.dataSources.dataBase.extensions

import com.playrion.picturesviewer.data.dataSources.dataBase.room.PictureAndInfo
import com.playrion.picturesviewer.domain.models.FlickrPicture

fun PictureAndInfo.asDomain(): FlickrPicture {
    return FlickrPicture(
        id = this.flickr.remoteId,
        title = this.flickr.title,
        thumbnailUrl = this.flickr.thumbnailUrl,
        description = this.info.description,
        imageUrl = this.flickr.pictureUrl,
        uploadDate = this.info.dateUploaded,
    )
}