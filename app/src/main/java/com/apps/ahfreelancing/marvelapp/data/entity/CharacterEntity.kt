package com.apps.ahfreelancing.marvelapp.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmed Hassan on 7/25/2019.
 */
class CharacterEntity {
    @SerializedName("id") var id: Int = 0
    @SerializedName("name") var name: String = ""
    @SerializedName("description") var description: String = ""
    @SerializedName("resourceURI") var imageURI: String = ""
    @SerializedName("thumbnail") var thumbnail: ImageEntity = ImageEntity()
    @SerializedName("urls") var urls: List<URLEntity> = emptyList()

}