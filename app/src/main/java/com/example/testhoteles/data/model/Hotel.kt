package com.example.testhoteles.data.model


import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.ArrayList
@Entity(tableName = "hotel_table")
class Hotel() : Parcelable {
    @SerializedName("address")
    var address: String? = null
    @SerializedName("amenities")
    //@Relation(parentColumn = "amenity_id", entityColumn = "amenity_id")
    @Ignore var amenities: List<Amenity?>? = null
    @PrimaryKey
    @ColumnInfo(name = "hotel_id")
    @SerializedName("id")
    var id: String = "-1"
    @SerializedName("main_picture")
    var mainPicture: String? = null
    @SerializedName("name")
    @ColumnInfo(name = "hotel_name")
    var name: String? = null
    @SerializedName("description")
    @ColumnInfo(name = "hotel_description")
    var description: String? = null
    @SerializedName("price")
    @Embedded
    var price: Price? = null
    @SerializedName("rating")
    var rating: Double? = null
    @SerializedName("stars")
    var stars: Float? = null
    @SerializedName("geo_location")
    @Embedded
    var geoLocation: GeoLocation? = null
    @SerializedName("city")
    @Ignore var city: CountryStateCity? = null
    @SerializedName("reviews")
    @Ignore var reviews: ArrayList<Review?>? = null

    constructor(parcel: Parcel) : this() {
        address = parcel.readString()
        amenities = parcel.createTypedArrayList(Amenity)
        id = parcel.readString()
        mainPicture = parcel.readString()
        name = parcel.readString()
        description = parcel.readString()
        price = parcel.readParcelable(Price::class.java.classLoader)
        rating = parcel.readValue(Double::class.java.classLoader) as? Double
        stars = parcel.readValue(Float::class.java.classLoader) as? Float
        geoLocation = parcel.readParcelable(GeoLocation::class.java.classLoader)
        city = parcel.readParcelable(CountryStateCity::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeTypedList(amenities)
        parcel.writeString(id)
        parcel.writeString(mainPicture)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeParcelable(price, flags)
        parcel.writeValue(rating)
        parcel.writeValue(stars)
        parcel.writeParcelable(geoLocation, flags)
        parcel.writeParcelable(city, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hotel> {
        override fun createFromParcel(parcel: Parcel): Hotel {
            return Hotel(parcel)
        }

        override fun newArray(size: Int): Array<Hotel?> {
            return arrayOfNulls(size)
        }
    }

}