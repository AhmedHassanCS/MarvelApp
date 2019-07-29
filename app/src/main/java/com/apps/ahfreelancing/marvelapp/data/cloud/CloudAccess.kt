package com.apps.ahfreelancing.marvelapp.data.cloud

import com.apps.ahfreelancing.marvelapp.BuildConfig
import com.apps.ahfreelancing.marvelapp.data.entity.CharacterEntity
import com.apps.ahfreelancing.marvelapp.data.entity.DataWrapperEntity
import com.apps.ahfreelancing.marvelapp.data.entity.MediaEntity
import com.apps.ahfreelancing.marvelapp.data.provider.RetrofitProvider


/**
 * Created by Ahmed Hassan on 7/26/2019.
 */
class CloudAccess {


    private val marvelAPI: MarvelAPI = RetrofitProvider.getRetrofit()!!.create(MarvelAPI::class.java)

    //Get all characters from the api in pages
    suspend fun getCharacters(page: Int): DataWrapperEntity<CharacterEntity>? {
        //characters to skip based on page number
        val skip = BuildConfig.PAGE_SIZE * (page - 1)
        //timestamp to use as salt in encryption
        val ts = System.currentTimeMillis().toString()
        //hash is the encryption of the concat of (timestamp, private key and public key)
        val hash = Encryption.md5(ts + BuildConfig.PRIVATE_KEY + BuildConfig.API_KEY)
        var result: DataWrapperEntity<CharacterEntity>?
        try {
            result = marvelAPI.getCharacters(BuildConfig.API_KEY, hash, ts, BuildConfig.PAGE_SIZE, skip).body()
        } catch (error: Exception) {
            result = DataWrapperEntity()
            result.code = 303
            result.status = "Connection Error"
        }
        return result
    }

    //get characters by name
    suspend fun getCharacters(name: String): DataWrapperEntity<CharacterEntity>? {
        val ts = System.currentTimeMillis().toString()
        val hash = Encryption.md5(ts + BuildConfig.PRIVATE_KEY + BuildConfig.API_KEY)

        var result: DataWrapperEntity<CharacterEntity>?
        try {
            result = marvelAPI.getCharacters(BuildConfig.API_KEY, hash, ts, name).body()
        } catch (error: Exception) {
            result = DataWrapperEntity()
            result.code = 303
            result.status = "Connection Error"
        }
        return result
    }

    //get all comics for a specific character
    suspend fun getComics(characterId: Int): DataWrapperEntity<MediaEntity>? {
        val ts = System.currentTimeMillis().toString()
        val hash = Encryption.md5(ts + BuildConfig.PRIVATE_KEY + BuildConfig.API_KEY)
        var result: DataWrapperEntity<MediaEntity>?
        try {
            result = marvelAPI.getComics(characterId, BuildConfig.API_KEY, hash, ts).body()
        } catch (error: Exception) {
            result = DataWrapperEntity()
            result.code = 303
            result.status = "Connection Error"
        }
        return result
    }

    //get all series for a specific character
    suspend fun getSeries(characterId: Int): DataWrapperEntity<MediaEntity>? {
        val ts = System.currentTimeMillis().toString()
        val hash = Encryption.md5(ts + BuildConfig.PRIVATE_KEY + BuildConfig.API_KEY)

        var result: DataWrapperEntity<MediaEntity>?
        try {
            result = marvelAPI.getSeries(characterId, BuildConfig.API_KEY, hash, ts).body()
        } catch (error: Exception) {
            result = DataWrapperEntity()
            result.code = 303
            result.status = "Connection Error"
        }
        return result
    }

    //get all stories for a specific character
    suspend fun getStories(characterId: Int): DataWrapperEntity<MediaEntity>? {
        val ts = System.currentTimeMillis().toString()
        val hash = Encryption.md5(ts + BuildConfig.PRIVATE_KEY + BuildConfig.API_KEY)

        var result: DataWrapperEntity<MediaEntity>?
        try {
            result = marvelAPI.getStories(characterId, BuildConfig.API_KEY, hash, ts).body()
        } catch (error: Exception) {
            result = DataWrapperEntity()
            result.code = 303
            result.status = "Connection Error"
        }
        return result
    }

    //get all events for a specific character
    suspend fun getEvents(characterId: Int): DataWrapperEntity<MediaEntity>? {
        val ts = System.currentTimeMillis().toString()
        val hash = Encryption.md5(ts + BuildConfig.PRIVATE_KEY + BuildConfig.API_KEY)

        var result: DataWrapperEntity<MediaEntity>?
        try {
            result = marvelAPI.getEvents(characterId, BuildConfig.API_KEY, hash, ts).body()
        } catch (error: Exception) {
            result = DataWrapperEntity()
            result.code = 303
            result.status = "Connection Error"
        }
        return result
    }
}