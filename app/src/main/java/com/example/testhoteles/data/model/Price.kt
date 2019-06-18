package com.example.testhoteles.data.model


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
data class Price(
    @SerializedName("base")
    var base: Int?,
    @SerializedName("best")
    var best: Int?,
    @SerializedName("currency")
    @Embedded
    var currency: Currency?,
    @SerializedName("final_price")
    var finalPrice: Boolean?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readParcelable(Currency::class.java.classLoader),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(base)
        parcel.writeValue(best)
        parcel.writeParcelable(currency, flags)
        parcel.writeValue(finalPrice)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Price> {
        override fun createFromParcel(parcel: Parcel): Price {
            return Price(parcel)
        }

        override fun newArray(size: Int): Array<Price?> {
            return arrayOfNulls(size)
        }
    }

}