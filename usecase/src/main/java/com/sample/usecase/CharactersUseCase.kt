package com.sample.usecase

import com.sample.domain.repository.CharactersRepository
import com.sample.domain.model.Character
import io.reactivex.Single

class CharactersUseCase(private val repository: CharactersRepository) {

    operator fun invoke(): Single<List<Character>> {
        return repository.getCharacters()
    }
}