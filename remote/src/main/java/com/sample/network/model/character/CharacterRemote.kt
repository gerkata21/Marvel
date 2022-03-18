package com.sample.network.model.character

data class CharacterRemote(
    var id: Long = 0,
    var name: String? = null,
    var description: String? = null,
    var thumbnail: ThumbnailRemote? = null
)