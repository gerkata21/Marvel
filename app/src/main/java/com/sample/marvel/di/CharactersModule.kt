package com.sample.marvel.di

import com.sample.data.repository.CharactersRepositoryImp
import com.sample.marvel.framework.data.datasource.CharactersLocalDataSourceImp
import com.sample.marvel.ui.characters.viewmodel.CharactersViewModel
import com.sample.usecase.CharactersUseCase
import com.sample.usecase.SearchUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {
    single { CharactersLocalDataSourceImp(get()) }
    single { CharactersRepositoryImp(get<CharactersLocalDataSourceImp>(), get()) }
    single { CharactersUseCase(get<CharactersRepositoryImp>()) }
    single { SearchUseCase(get<CharactersRepositoryImp>()) }
    viewModel { CharactersViewModel(get(), get()) }
}