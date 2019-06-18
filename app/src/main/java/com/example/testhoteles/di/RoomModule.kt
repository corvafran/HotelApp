package com.example.testhoteles.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.testhoteles.local.db.HotelDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule{

    @Provides
    @Singleton
    fun hotelDatabase(context: Context): HotelDatabase {
        return Room.databaseBuilder(context.applicationContext,
            HotelDatabase::class.java,
            "hotel_database")
            .build()
    }


    @Provides
    fun hotelDao(database: HotelDatabase) = database.hotelDao()
}