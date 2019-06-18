package com.example.testhoteles.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testhoteles.data.model.Hotel
import io.reactivex.Single

@Dao
interface HotelDao {

   @Query("SELECT * from hotel_table")
    fun getHotels(): Single<MutableList<Hotel>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(hotels: List<Hotel>)
}
