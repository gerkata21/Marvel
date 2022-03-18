package com.sample.marvel.framework.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sample.marvel.framework.data.db.converters.ThumbnailConverter
import com.sample.marvel.framework.data.db.dao.CharactersDao
import com.sample.marvel.framework.data.db.entity.CharacterEntity
import com.sample.marvel.framework.data.db.entity.ComicEntity

const val DATA_BASE_NAME = "movies_db"

@Database(
    entities = [CharacterEntity::class, ComicEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ThumbnailConverter::class)
abstract class MarvelDatabase : RoomDatabase() {
    abstract val charactersDao: CharactersDao
}