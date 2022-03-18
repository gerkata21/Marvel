package com.sample.usecase

import com.sample.domain.repository.CharactersRepository
import com.sample.domain.model.Character
import io.reactivex.Single

class SearchUseCase(private val repository: CharactersRepository) {

    operator fun invoke(query: String): Single<List<Character>> {
        return repository.search(query)
    }

}