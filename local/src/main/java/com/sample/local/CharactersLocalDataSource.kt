package com.sample.local

import io.reactivex.Single

interface CharactersLocalDataSource<T> {
    fun getCharacters(): Single<List<T>>
    fun deleteAllCharacters()
    fun insertAllCharacters(characters: List<T>)
}