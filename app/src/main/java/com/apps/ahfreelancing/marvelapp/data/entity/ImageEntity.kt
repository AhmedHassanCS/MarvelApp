package com.apps.ahfreelancing.marvelapp.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmed Hassan on 7/26/2019.
 */
class ImageEntity {
    @SerializedName("path") var path: String = ""
    @SerializedName("extension") var extension: String = ""
}