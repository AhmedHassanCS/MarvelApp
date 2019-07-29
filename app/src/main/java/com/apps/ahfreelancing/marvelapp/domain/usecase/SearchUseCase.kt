package com.apps.ahfreelancing.marvelapp.domain.usecase

import com.apps.ahfreelancing.marvelapp.domain.model.CharacterModel
import com.apps.ahfreelancing.marvelapp.domain.model.DataWrapperModel
import com.apps.ahfreelancing.marvelapp.domain.repository.ICharacterRepository
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 7/27/2019.
 */
class SearchUseCase @Inject constructor(val iCharacterRepository: ICharacterRepository) : BaseUseCase<String, List<CharacterModel>>() {

    override suspend fun execute(param: String): DataWrapperModel<List<CharacterModel>> {
        return iCharacterRepository.getCharacters(param)
    }

}