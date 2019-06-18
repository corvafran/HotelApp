package com.example.testhoteles.data.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Comments(
    @SerializedName("good")
    var good: String?,
    @SerializedName("bad")
    var bad: String?,
    @SerializedName("type")
    var type: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(good)
        parcel.writeString(bad)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comments> {
        override fun createFromParcel(parcel: Parcel): Comments {
            return Comments(parcel)
        }

        override fun newArray(size: Int): Array<Comments?> {
            return arrayOfNulls(size)
        }
    }

}