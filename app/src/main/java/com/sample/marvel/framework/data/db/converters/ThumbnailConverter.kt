package com.sample.marvel.framework.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.marvel.framework.data.db.entity.ThumbnailEntity

class ThumbnailConverter {
    @TypeConverter
    fun fromThumbnail(thumbnail: ThumbnailEntity?): String? {
        if (thumbnail == null) {
            return null
        }
        val type =
            object : TypeToken<ThumbnailEntity?>() {}.type
        return Gson().toJson(thumbnail, type)
    }

    @TypeConverter
    fun toThumbnail(thumbnail1: String?): ThumbnailEntity? {
        if (thumbnail1 == null) {
            return null
        }
        val type =
            object : TypeToken<ThumbnailEntity?>() {}.type
        return Gson().fromJson(thumbnail1, type)
    }
}