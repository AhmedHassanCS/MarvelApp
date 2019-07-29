package com.apps.ahfreelancing.marvelapp.data.repository

import com.apps.ahfreelancing.marvelapp.data.entity.CharacterEntity
import com.apps.ahfreelancing.marvelapp.data.entity.DataWrapperEntity
import com.apps.ahfreelancing.marvelapp.data.entity.ImageEntity
import com.apps.ahfreelancing.marvelapp.data.entity.MediaEntity
import com.apps.ahfreelancing.marvelapp.domain.model.CharacterModel
import com.apps.ahfreelancing.marvelapp.domain.model.DataWrapperModel
import com.apps.ahfreelancing.marvelapp.domain.model.MediaModel

/**
 * Created by Ahmed Hassan on 7/26/2019.
 *
 * EntityMapper helps to map between entities returned from server and application models
 */
object EntityMapper {
    fun mapCharacters(dataWrapper: DataWrapperEntity<CharacterEntity>): DataWrapperModel<List<CharacterModel>> {
        val characters = ArrayList<CharacterModel>(dataWrapper.dataContainer.results.size)

        for (entity in dataWrapper.dataContainer.results) {
            var detailUrl = ""
            var wikiUrl = ""
            var comicUrl = ""

            for (url in entity.urls)
                when (url.type) {
                    "detail" -> detailUrl = url.url
                    "wiki" -> wikiUrl = url.url
                    "comiclink" -> comicUrl = url.url
                }

            val model = CharacterModel(
                entity.id,
                entity.name,
                entity.description,
                entity.imageURI,
                entity.thumbnail.path + '.' + entity.thumbnail.extension,
                detailUrl, wikiUrl, comicUrl
            )

            characters.add(model)
        }

        return DataWrapperModel(dataWrapper.code, dataWrapper.status, characters.toList())

    }

    fun mapMedia(dataWrapper: DataWrapperEntity<MediaEntity>): List<MediaModel> {
        val models = List<MediaModel>(dataWrapper.dataContainer.results.size) {
            val entity = dataWrapper.dataContainer.results[it]
            if (entity.thumbnail == null) entity.thumbnail = ImageEntity()
            MediaModel(
                entity.title,
                entity.thumbnail.path + '.' + entity.thumbnail.extension
            )
        }

        return models
    }

}