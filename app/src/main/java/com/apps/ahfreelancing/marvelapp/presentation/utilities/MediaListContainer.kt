package com.apps.ahfreelancing.marvelapp.presentation.utilities

import android.os.Parcel
import android.os.Parcelable
import com.apps.ahfreelancing.marvelapp.domain.model.MediaModel

/**
 * Created by Ahmed Hassan on 7/29/2019.
 */
class MediaListContainer() : ArrayList<MediaModel>(), Parcelable {
    constructor(parcel: Parcel) : this()

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MediaListContainer> {
        override fun createFromParcel(parcel: Parcel): MediaListContainer {
            return MediaListContainer(parcel)
        }

        override fun newArray(size: Int): Array<MediaListContainer?> {
            return arrayOfNulls(size)
        }
    }
}