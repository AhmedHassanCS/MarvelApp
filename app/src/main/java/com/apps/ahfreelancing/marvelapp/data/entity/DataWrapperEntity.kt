package com.apps.ahfreelancing.marvelapp.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmed Hassan on 7/25/2019.
 */
class DataWrapperEntity<T>{
    @SerializedName("code") var code: Int = 0
    @SerializedName("status") var status: String = ""
    @SerializedName("attributionText") var attributionText: String = ""
    @SerializedName("data") var dataContainer: DataContainerEntity<T> = DataContainerEntity()
}