package com.apps.ahfreelancing.marvelapp.domain.usecase

import com.apps.ahfreelancing.marvelapp.domain.model.CharacterMediaModel
import com.apps.ahfreelancing.marvelapp.domain.model.DataWrapperModel
import com.apps.ahfreelancing.marvelapp.domain.repository.ICharacterRepository
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 7/28/2019.
 */
class CharacterDetailsUseCase @Inject constructor(val iCharacterRepository: ICharacterRepository) : BaseUseCase<Int, CharacterMediaModel>() {

    //Gets all media including comics, series, stories and events
    override suspend fun execute(param: Int): DataWrapperModel<CharacterMediaModel> {
        return iCharacterRepository.getCharacterMedia(param)
    }

}