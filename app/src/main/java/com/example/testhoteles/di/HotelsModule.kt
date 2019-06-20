package com.example.testhoteles.di


import android.provider.SyncStateContract
import androidx.lifecycle.ViewModelProvider
import com.example.testhoteles.data.model.Hotel
import com.example.testhoteles.data.repositories.HotelsRepository
import com.example.testhoteles.data.remote.ApiServices
import com.example.testhoteles.local.db.HotelDao
import com.example.testhoteles.local.db.HotelDatabase
import com.example.testhoteles.ui.hotelDetail.HotelDetailActivity
import com.example.testhoteles.ui.hotelDetail.HotelDetailViewModel
import com.example.testhoteles.ui.main.MainViewModel
import com.example.testhoteles.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Scope
import javax.inject.Singleton

@Module
class HotelsModule {
   /* @Provides
    @Singleton
    fun mainViewModel(repository: HotelsRepository): MainViewModel {
        return MainViewModel(repository)
    }*/

   /* @Provides
    fun hotelDetailViewModel(repository: HotelsRepository):  HotelDetailViewModel{
        return HotelDetailViewModel(repository)
    }*/

    @Provides
    fun repository(apiServices: ApiServices, hotelDao: HotelDao): HotelsRepository {
        return HotelsRepository(apiServices, hotelDao)
    }

    @Provides
    fun apiServices(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }



    @Provides
    fun retrofit(httpClient: OkHttpClient, gsonConverter: GsonConverterFactory, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl("http://private-a2ba2-jovenesdealtovuelo.apiary-mock.com/")
            .addConverterFactory(gsonConverter)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun okHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }


    @Provides
    fun gson(): Gson {
        return GsonBuilder().create()
    }


    @Provides
    fun gsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

}