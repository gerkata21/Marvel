package com.sample.marvel.framework.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey
    var id: Long = 0,
    var name: String? = null,
    var description: String? = null,
    var thumbnail: ThumbnailEntity? = null
)
