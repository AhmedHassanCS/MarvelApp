package com.apps.ahfreelancing.marvelapp.domain.usecase

import com.apps.ahfreelancing.marvelapp.domain.model.CharacterModel
import com.apps.ahfreelancing.marvelapp.domain.model.DataWrapperModel
import com.apps.ahfreelancing.marvelapp.domain.repository.ICharacterRepository
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 7/26/2019.
 */
class CharactersUseCase @Inject constructor(val iCharacterRepository: ICharacterRepository) : BaseUseCase<Int, List<CharacterModel>>() {


    override suspend fun execute(param: Int): DataWrapperModel<List<CharacterModel>> {
        return iCharacterRepository.getCharacters(param)
    }

}