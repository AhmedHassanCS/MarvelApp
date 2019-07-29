package com.apps.ahfreelancing.marvelapp.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmed Hassan on 7/25/2019.
 */
class DataContainerEntity <T>{
    @SerializedName("count") var count: Int = 0
    @SerializedName("results") var results: List<T> = listOf()
}