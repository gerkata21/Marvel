package com.sample.marvel.framework.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comic")
data class ComicEntity(
    @PrimaryKey
    val id: Long,
    var title: String? = null,
    var thumbnail: ThumbnailEntity? = null,

    @ColumnInfo(name = "character_id")
    var characterId: Long = 0
)