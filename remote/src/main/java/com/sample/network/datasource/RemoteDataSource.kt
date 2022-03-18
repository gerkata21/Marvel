package com.sample.network.datasource

import com.sample.network.model.character.CharactersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteDataSource {

    @GET("characters")
    fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Single<CharactersResponse>

    @GET("characters")
    fun search(@Query("nameStartsWith") query: String?): Single<CharactersResponse>
}
