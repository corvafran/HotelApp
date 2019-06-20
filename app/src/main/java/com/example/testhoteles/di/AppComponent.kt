package com.example.testhoteles.di

import com.example.testhoteles.data.model.Hotel
import com.example.testhoteles.ui.base.BaseActivity
import com.example.testhoteles.ui.hotelDetail.HotelDetailActivity
import com.example.testhoteles.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [HotelsModule::class, ContextModule::class, RoomModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: HotelDetailActivity)
}