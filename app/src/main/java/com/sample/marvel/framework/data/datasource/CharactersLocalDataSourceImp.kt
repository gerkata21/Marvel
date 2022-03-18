package com.sample.marvel.framework.data.datasource

import com.sample.domain.model.Character
import com.sample.local.CharactersLocalDataSource
import com.sample.marvel.framework.data.db.dao.CharactersDao
import com.sample.marvel.framework.data.mapper.mapToCharacterEntityList
import com.sample.marvel.framework.data.mapper.mapToCharacterList
import io.reactivex.Single

class CharactersLocalDataSourceImp(private val dao: CharactersDao) :
    CharactersLocalDataSource<Character> {

    override fun getCharacters(): Single<List<Character>> {
        return dao.allCharacters.flatMap { charactersEntityList ->
            if (charactersEntityList.isNullOrEmpty()) {
                Single.error(Throwable("No cache"))
            } else {
                Single.just(charactersEntityList.mapToCharacterList())
            }
        }
    }

    override fun deleteAllCharacters() {
        dao.deleteAll()
    }

    override fun insertAllCharacters(characters: List<Character>) {
        dao.insertAllCharacters(characters.mapToCharacterEntityList())
    }
}