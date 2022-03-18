package com.sample.marvel.ui.characters.viewmodel

import androidx.lifecycle.ViewModel
import com.sample.marvel.framework.extensions.toError
import com.sample.marvel.framework.extensions.toLoading
import com.sample.marvel.framework.extensions.toSuccess
import com.sample.marvel.ui.DataWrapper
import com.sample.marvel.ui.characters.model.CharacterUiModel
import com.sample.marvel.ui.characters.model.mapToCharacterUiList
import com.sample.network.utils.handleError
import com.sample.usecase.CharactersUseCase
import com.sample.usecase.SearchUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

class CharactersViewModel(
    private val searchUseCase: SearchUseCase,
    private val charactersUseCase: CharactersUseCase
) :
    ViewModel() {

    private lateinit var disposable: Disposable

    private val charactersSubject = BehaviorSubject.create<DataWrapper<List<CharacterUiModel>>>()

    private val searchCharacterSubject =
        BehaviorSubject.create<DataWrapper<List<CharacterUiModel>>>()

    fun getCharacters(query: String? = null) {
        if (query.isNullOrEmpty()) {
            disposable = charactersUseCase()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { charactersSubject.toLoading() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        charactersSubject.toSuccess(result.mapToCharacterUiList())
                    },
                    { onError ->
                        charactersSubject.toError(onError.handleError())
                    })
        } else {
            disposable = searchUseCase(query)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { searchCharacterSubject.toLoading() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        searchCharacterSubject.toSuccess(result.mapToCharacterUiList())
                    },
                    { onError ->
                        searchCharacterSubject.toError(onError.handleError())
                    })

        }
    }

    fun observeCharactersChanges(): Subject<DataWrapper<List<CharacterUiModel>>> {
        return charactersSubject
    }

    fun observeSearchChanges(): Subject<DataWrapper<List<CharacterUiModel>>> {
        return searchCharacterSubject
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}
