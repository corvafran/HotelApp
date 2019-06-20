package com.example.testhoteles.data.repositories

import com.example.testhoteles.data.model.Hotel
import com.example.testhoteles.data.remote.ApiServices
import com.example.testhoteles.data.remote.response.HotelResponse
import com.example.testhoteles.local.db.HotelDao
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HotelsRepository @Inject constructor(private val apiServices: ApiServices, private val hotelDao: HotelDao) {
    fun getAllHotels(): Single<MutableList<Hotel>?> {
        var observable = apiServices.getHotels()
            .map { response -> response.items }
            .doOnSuccess { hotels -> hotelDao.insertAll(hotels as List<Hotel>) }
        return observable;
    }

    fun getAllHotelsFromDb(): Single<MutableList<Hotel>?>{
        return hotelDao.getHotels()
    }

    fun getQueryHotelsFromDb(query: String): Single<MutableList<Hotel>?>{
        var search = "%$query%";
        return hotelDao.getQueryHotels(search)
    }

    fun getHotel(id: String): Single<Hotel?> {
        var observable = apiServices.getHotelDetail(id)
            .map { hotelResponse: HotelResponse ->
                var hotel = hotelResponse.hotel
                hotel?.price = hotelResponse.price
                hotel
            }
        return observable;
    }
}