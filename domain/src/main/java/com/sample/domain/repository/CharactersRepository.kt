package com.sample.domain.repository

import com.sample.domain.model.Character
import io.reactivex.Single

interface CharactersRepository {
    fun getCharacters(): Single<List<Character>>
    fun search(query: String): Single<List<Character>>
}