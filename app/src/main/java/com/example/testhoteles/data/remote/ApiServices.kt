package com.example.testhoteles.data.remote

import com.example.testhoteles.data.remote.response.HotelListResponse
import com.example.testhoteles.data.remote.response.HotelResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("hotels")
    fun getHotels(): Single<HotelListResponse>

    @GET("hotels/{hotel_id}")
    fun getHotelDetail(@Path("hotel_id") hotelId: String): Single<HotelResponse>
}