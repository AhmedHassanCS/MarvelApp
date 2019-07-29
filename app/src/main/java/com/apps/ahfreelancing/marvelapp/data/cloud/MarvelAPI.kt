package com.apps.ahfreelancing.marvelapp.data.cloud

import com.apps.ahfreelancing.marvelapp.data.entity.CharacterEntity
import com.apps.ahfreelancing.marvelapp.data.entity.MediaEntity
import com.apps.ahfreelancing.marvelapp.data.entity.DataWrapperEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Ahmed Hassan on 7/26/2019.
 */
interface MarvelAPI {
    @GET("v1/public/characters")
    suspend fun getCharacters(@Query("apikey") apiKey: String,
                              @Query("hash") hash: String,
                              @Query("ts") timestamp: String,
                              @Query("limit") limit: Int,
                              @Query("offset") offset: Int)
            : Response<DataWrapperEntity<CharacterEntity>>

    @GET("v1/public/characters")
    suspend fun getCharacters(@Query("apikey") apiKey: String,
                              @Query("hash") hash: String,
                              @Query("ts") timestamp: String,
                              @Query("name") name: String)
            : Response<DataWrapperEntity<CharacterEntity>>

    @GET("v1/public/characters/{characterId}/comics")
    suspend fun getComics(
        @Path("characterId") id: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String)
        : Response<DataWrapperEntity<MediaEntity>>

    @GET("v1/public/characters/{characterId}/series")
    suspend fun getSeries(
        @Path("characterId") id: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String)
            : Response<DataWrapperEntity<MediaEntity>>

    @GET("v1/public/characters/{characterId}/stories")
    suspend fun getStories(
        @Path("characterId") id: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String)
            : Response<DataWrapperEntity<MediaEntity>>

    @GET("v1/public/characters/{characterId}/events")
    suspend fun getEvents(
        @Path("characterId") id: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String)
            : Response<DataWrapperEntity<MediaEntity>>
}