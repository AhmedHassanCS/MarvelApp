package com.apps.ahfreelancing.marvelapp.domain.model

/**
 * Created by Ahmed Hassan on 7/26/2019.
 */
data class DataWrapperModel<Data>(val code:Int, val message: String, val data: Data)