package com.example.testhoteles.db

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testhoteles.local.db.HotelDao
import com.example.testhoteles.local.db.HotelDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import androidx.test.core.app.ApplicationProvider
import com.example.testhoteles.data.model.Hotel


@RunWith(AndroidJUnit4::class)
class DbTest {
    private lateinit var hotelDao: HotelDao
    private lateinit var db: HotelDatabase
    val hotels = mutableListOf<Hotel>()

    @Before
    fun createDb() {
        hotels.addAll(DbTestData.getTestHotel())
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, HotelDatabase::class.java).build()
        hotelDao = db.hotelDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeHotelsAndQueryInList() {
        hotelDao.insertAll(hotels)
        val querySingle = hotelDao.getQueryHotels("%Grand Beach Hotel%")
        querySingle.test()
            .assertValue {
                it.isNotEmpty()
            }
    }
}