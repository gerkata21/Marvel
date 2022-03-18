package com.sample.marvel.framework.data.mapper

import com.sample.domain.model.Thumbnail
import com.sample.marvel.framework.data.db.entity.ThumbnailEntity

fun Thumbnail.mapToThumbnailEntity(): ThumbnailEntity {
    return ThumbnailEntity(path = path, extension = extension)
}

fun ThumbnailEntity.mapToThumbnail(): Thumbnail {
    return Thumbnail(path = path, extension = extension)
}