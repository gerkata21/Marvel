package com.sample.marvel.ui.characters.model

import android.os.Parcel
import android.os.Parcelable

data class ThumbnailUiModel(
    var path: String? = null,
    var extension: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(path)
        parcel.writeString(extension)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ThumbnailUiModel> {
        override fun createFromParcel(parcel: Parcel): ThumbnailUiModel {
            return ThumbnailUiModel(parcel)
        }

        override fun newArray(size: Int): Array<ThumbnailUiModel?> {
            return arrayOfNulls(size)
        }
    }
}