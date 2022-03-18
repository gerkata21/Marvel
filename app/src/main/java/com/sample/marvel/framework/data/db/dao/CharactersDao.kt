package com.sample.marvel.framework.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.marvel.framework.data.db.entity.CharacterEntity
import io.reactivex.Single

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCharacters(characterList: List<CharacterEntity>)

    @get:Query("SELECT * FROM character")
    val allCharacters: Single<List<CharacterEntity>>

    @Query("DELETE FROM character")
    fun deleteAll()
}