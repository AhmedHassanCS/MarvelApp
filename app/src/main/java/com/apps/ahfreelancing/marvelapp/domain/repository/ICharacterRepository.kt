package com.apps.ahfreelancing.marvelapp.domain.repository

import com.apps.ahfreelancing.marvelapp.domain.model.CharacterMediaModel
import com.apps.ahfreelancing.marvelapp.domain.model.CharacterModel
import com.apps.ahfreelancing.marvelapp.domain.model.DataWrapperModel

/**
 * Created by Ahmed Hassan on 7/26/2019.
 */
interface ICharacterRepository{
    suspend fun getCharacters(page: Int) : DataWrapperModel<List<CharacterModel>>

    suspend fun getCharacters(name: String) : DataWrapperModel<List<CharacterModel>>

    suspend fun getCharacterMedia(id: Int) : DataWrapperModel<CharacterMediaModel>

}