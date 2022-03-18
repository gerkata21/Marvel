package com.sample.marvel

import android.app.Application
import com.sample.marvel.di.charactersModule
import com.sample.marvel.di.dataModule
import com.sample.marvel.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoinInjection()

        // TODO: 10/7/2020
        /**
         * Replace png with vector
         * Separated models per layer
         */
    }

    private fun startKoinInjection() {
        startKoin {
            androidContext(this@MarvelApplication)
            modules(
                listOf(
                    charactersModule,
                    dataModule,
                    networkModule
                )
            )
        }
    }
}