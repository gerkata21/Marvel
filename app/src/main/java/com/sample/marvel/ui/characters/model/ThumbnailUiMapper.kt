package com.sample.marvel.ui.characters.model

import com.sample.domain.model.Thumbnail

fun ThumbnailUiModel.mapToThumbnail(): Thumbnail {
    return Thumbnail(path = path, extension = extension)
}

fun Thumbnail.mapToThumbnailUiModel(): ThumbnailUiModel {
    return ThumbnailUiModel(path = path, extension = extension)
}