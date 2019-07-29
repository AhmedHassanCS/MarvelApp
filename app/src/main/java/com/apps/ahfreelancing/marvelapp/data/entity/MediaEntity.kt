package com.apps.ahfreelancing.marvelapp.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmed Hassan on 7/27/2019.
 */
class MediaEntity {
    @SerializedName("title") var title: String = ""
    @SerializedName("thumbnail") var thumbnail: ImageEntity = ImageEntity()
}