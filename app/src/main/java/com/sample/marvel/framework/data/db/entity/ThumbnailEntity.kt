package com.sample.marvel.framework.data.db.entity

import androidx.room.Entity

@Entity(tableName = "thumbnails")
data class ThumbnailEntity(
    var path: String? = null,
    var extension: String? = null
)