package com.sample.marvel.di

import androidx.room.Room
import com.sample.marvel.framework.data.db.DATA_BASE_NAME
import com.sample.marvel.framework.data.db.MarvelDatabase
import org.koin.dsl.module

val dataModule = module {
    single { Room.databaseBuilder(get(), MarvelDatabase::class.java, DATA_BASE_NAME).build() }
    single { get<MarvelDatabase>().charactersDao }
}