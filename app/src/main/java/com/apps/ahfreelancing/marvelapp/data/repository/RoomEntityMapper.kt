package com.apps.ahfreelancing.marvelapp.data.repository

import com.apps.ahfreelancing.marvelapp.data.cache.RoomCharacterEntity
import com.apps.ahfreelancing.marvelapp.domain.model.CharacterModel
import com.apps.ahfreelancing.marvelapp.domain.model.DataWrapperModel

/**
 * Created by Ahmed Hassan on 7/28/2019.
 *
 * RoomEntityMapper helps in converting from room entities to application models and vise versa
 */
object RoomEntityMapper {
    fun mapCharactersToModel(entities: List<RoomCharacterEntity>): List<CharacterModel> {
        val characters = ArrayList<CharacterModel>(entities.size)
        for(entity in entities){
            characters.add(
                CharacterModel(
                    entity.id,
                    entity.name,
                    entity.description,
                    entity.imageURI,
                    entity.thumbnail,
                    entity.detailUrl,
                    entity.wikiUrl,
                    entity.comicUrl
                ))
        }
        return characters.toList()
    }

    fun mapCharactersToEntity(models: List<CharacterModel>): List<RoomCharacterEntity>{
        val entities = ArrayList<RoomCharacterEntity>(models.size)

        for(model in models){
            entities.add(
                RoomCharacterEntity(
                    model.id,
                    model.name,
                    model.description,
                    model.imageURI,
                    model.thumbnail,
                    model.detailUrl,
                    model.wikiUrl,
                    model.comicUrl
                ))
        }

        return entities
    }
}