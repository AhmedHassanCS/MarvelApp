package com.apps.ahfreelancing.marvelapp.data.repository

import android.content.Context
import com.apps.ahfreelancing.marvelapp.BuildConfig
import com.apps.ahfreelancing.marvelapp.data.cache.CharacterRoomDatabase
import com.apps.ahfreelancing.marvelapp.data.cloud.CloudAccess
import com.apps.ahfreelancing.marvelapp.data.repository.RoomEntityMapper.mapCharactersToEntity
import com.apps.ahfreelancing.marvelapp.data.repository.RoomEntityMapper.mapCharactersToModel
import com.apps.ahfreelancing.marvelapp.domain.model.CharacterMediaModel
import com.apps.ahfreelancing.marvelapp.domain.model.CharacterModel
import com.apps.ahfreelancing.marvelapp.domain.model.DataWrapperModel
import com.apps.ahfreelancing.marvelapp.domain.repository.ICharacterRepository

/**
 * Created by Ahmed Hassan on 7/26/2019.
 */
class CharacterRepository(context: Context) : ICharacterRepository {

    private val cloudAccess = CloudAccess()

    private val charactersDao = CharacterRoomDatabase.getDatabase(context).characterDao()


    override suspend fun getCharacters(page: Int) : DataWrapperModel<List<CharacterModel>> {
        val result = EntityMapper.mapCharacters(cloudAccess.getCharacters(page)!!)
        //If results not retrieved correctly from server fetch it from cache
        if(result.code != 200){
            val cacheCharacters = charactersDao.getAll(
                BuildConfig.PAGE_SIZE * (page - 1),
                BuildConfig.PAGE_SIZE)

            if(cacheCharacters.isNotEmpty()){
                return DataWrapperModel(
                    200,"", mapCharactersToModel(cacheCharacters))
            }
        } else{
            //Update cache every time server respond with new data, on duplication replace
            charactersDao.insertAll(mapCharactersToEntity(result.data))
        }
        return result
    }

    //Get characters by name
    override suspend fun getCharacters(name: String): DataWrapperModel<List<CharacterModel>>{
        val result = EntityMapper.mapCharacters(cloudAccess.getCharacters(name)!!)

        //If results not retrieved correctly from server fetch it from cache
        if(result.code != 200){
            val cacheCharacters = charactersDao.getByName(name)
            if(cacheCharacters.isNotEmpty()){
                return DataWrapperModel(
                    200,"", mapCharactersToModel(cacheCharacters))
            }
        } else{
            //Update cache every time server respond with new data, on duplication replace
            charactersDao.insertAll(mapCharactersToEntity(result.data))
        }
        return result
    }

    override suspend fun getCharacterMedia(id: Int) : DataWrapperModel<CharacterMediaModel>{
        //Result is by default success
        var code = 200
        var status = "Success"
        val comics = EntityMapper.mapMedia(cloudAccess.getComics(id)!!)
        val series = EntityMapper.mapMedia(cloudAccess.getSeries(id)!!)
        val stories = EntityMapper.mapMedia(cloudAccess.getStories(id)!!)
        val events = EntityMapper.mapMedia(cloudAccess.getEvents(id)!!)

        //If lists are all empty, return error
        if(comics.isEmpty() && series.isEmpty() && stories.isEmpty() && events.isEmpty()){
            code = 303
            status = "No media can be loaded"
        }
        return DataWrapperModel(code, status, data = CharacterMediaModel(comics, series, stories, events))
    }

}