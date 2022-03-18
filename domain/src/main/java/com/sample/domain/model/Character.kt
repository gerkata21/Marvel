package com.sample.domain.model

data class Character(
    var id: Long = 0,
    var name: String? = null,
    var description: String? = null,
    var thumbnail: Thumbnail? = null
)