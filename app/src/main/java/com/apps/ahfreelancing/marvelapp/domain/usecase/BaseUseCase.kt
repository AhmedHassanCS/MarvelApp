package com.apps.ahfreelancing.marvelapp.domain.usecase

import com.apps.ahfreelancing.marvelapp.domain.model.DataWrapperModel

/**
 * Created by Ahmed Hassan on 7/26/2019.
 */
abstract class BaseUseCase<Param, Data> {
    abstract suspend fun execute(param: Param) : DataWrapperModel<Data>
}