package com.example.testhoteles.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testhoteles.data.model.*


@Database(entities = [Hotel::class, Amenity::class], version = 1)
abstract class HotelDatabase : RoomDatabase() {
    abstract fun hotelDao(): HotelDao
}
