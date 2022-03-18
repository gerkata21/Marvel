package com.sample.marvel.ui.characters.model

class CharacterUiModel constructor(
    var id: Long = 0,
    var name: String? = null,
    var description: String? = null,
    var thumbnailUiModel: ThumbnailUiModel? = null
)