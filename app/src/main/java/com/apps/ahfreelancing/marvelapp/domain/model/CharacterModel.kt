package com.apps.ahfreelancing.marvelapp.domain.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * Created by Ahmed Hassan on 7/26/2019.
 */
data class CharacterModel(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val imageURI: String = "",
    val thumbnail: String = "",
    val detailUrl: String = "",
    val wikiUrl: String = "",
    val comicUrl: String = ""
    ) : Parcelable {

    constructor(parcel: Parcel) : this()

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(imageURI)
        parcel.writeString(thumbnail)
        parcel.writeString(detailUrl)
        parcel.writeString(wikiUrl)
        parcel.writeString(comicUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterModel> {
        override fun createFromParcel(parcel: Parcel): CharacterModel {
            return CharacterModel(parcel)
        }

        override fun newArray(size: Int): Array<CharacterModel?> {
            return arrayOfNulls(size)
        }
    }
}