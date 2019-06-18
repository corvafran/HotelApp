package com.example.testhoteles.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity

class GeoLocation() : Parcelable {
    var latitude: Double? = null
    var longitude: Double? = null

    constructor(parcel: Parcel) : this() {
        latitude = parcel.readValue(Double::class.java.classLoader) as? Double
        longitude = parcel.readValue(Double::class.java.classLoader) as? Double
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(latitude)
        parcel.writeValue(longitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GeoLocation> {
        override fun createFromParcel(parcel: Parcel): GeoLocation {
            return GeoLocation(parcel)
        }

        override fun newArray(size: Int): Array<GeoLocation?> {
            return arrayOfNulls(size)
        }
    }
}