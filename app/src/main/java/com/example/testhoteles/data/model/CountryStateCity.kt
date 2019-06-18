package com.example.testhoteles.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
//@Entity(tableName = "country_state_city_table")
class CountryStateCity() : Parcelable {
  /*  @PrimaryKey
    @ColumnInfo(name= "country_state_city_id")*/
    var id: Int? = null
    var name: String? = null
   /* @SerializedName("country")
    @Embedded*/
    var country: CountryStateCity? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
        country = parcel.readParcelable(CountryStateCity::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeParcelable(country, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountryStateCity> {
        override fun createFromParcel(parcel: Parcel): CountryStateCity {
            return CountryStateCity(parcel)
        }

        override fun newArray(size: Int): Array<CountryStateCity?> {
            return arrayOfNulls(size)
        }
    }

}