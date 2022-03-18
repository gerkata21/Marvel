package com.sample.data.repository

import com.sample.data.mapToCharacterList
import com.sample.domain.model.Character
import com.sample.domain.repository.CharactersRepository
import com.sample.local.CharactersLocalDataSource
import com.sample.network.datasource.RemoteDataSource
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers.io

class CharactersRepositoryImp(
    private val localDataSource: CharactersLocalDataSource<Character>,
    private val remoteDataSource: RemoteDataSource
) : CharactersRepository {

    /**
     * This method tries to fetch data from LocalDataSource first
     * If it fails to do so it fetches the data from RemoteDataSource
     */
    override fun getCharacters(): Single<List<Character>> {
        return getDataFromLocalDataSource()
            .onErrorResumeNext(
                getDataFromRemoteSource()
            ).subscribeOn(io())
    }

    private fun getDataFromLocalDataSource(): Single<List<Character>> {
        return localDataSource.getCharacters()
    }

    private fun getDataFromRemoteSource(): Single<List<Character>?> {
        return remoteDataSource.getCharacters(0, 20)
            .flatMap { response ->
                response.data?.let {
                    Single.just(it.charactersList?.mapToCharacterList())
                }
            }
            .doOnSuccess { charactersList ->
                charactersList?.let { charactersRemoteList ->
                    localDataSource.insertAllCharacters(characters = charactersRemoteList)
                }
            }.doOnError {
                Single.error<Throwable>(it)
            }
    }

    override fun search(query: String): Single<List<Character>> {
        return remoteDataSource.search(query).flatMap { charactersResponse ->
            charactersResponse.data?.let {
                Single.just(it.charactersList?.mapToCharacterList())
            }
        }
    }
}