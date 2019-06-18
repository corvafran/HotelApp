package com.example.testhoteles.data.model


import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "amenity_table")
data class Amenity(
    @SerializedName("description")
    var description: String?,
    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name= "amenity_id")
    var id: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Amenity> {
        override fun createFromParcel(parcel: Parcel): Amenity {
            return Amenity(parcel)
        }

        override fun newArray(size: Int): Array<Amenity?> {
            return arrayOfNulls(size)
        }
    }

}