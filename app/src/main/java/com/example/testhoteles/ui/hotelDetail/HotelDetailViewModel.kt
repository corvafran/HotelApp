package com.example.testhoteles.ui.hotelDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testhoteles.data.model.Hotel
import com.example.testhoteles.data.repositories.HotelsRepository
import com.example.testhoteles.ui.base.BaseViewModel
import com.example.testhoteles.ui.base.ScreenState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named
import androidx.lifecycle.ViewModelProvider
import android.R.attr.dependency

class HotelDetailViewModel@Inject constructor(private val repository: HotelsRepository) : BaseViewModel() {
    val hotelLiveData : MutableLiveData<Hotel> = MutableLiveData()
    val screenStateLiveData : MutableLiveData<ScreenState> = MutableLiveData()
    var hotel: Hotel? = null

    fun start(hotel: Hotel) {
        this.hotel = hotel;
        if(hotelLiveData.value != null) return
        screenStateLiveData.postValue(ScreenState.Loading(true))
        var disposable = repository.getHotel(hotel.id!!)
            .subscribeOn(Schedulers.newThread())
            .doOnSuccess {
                this.hotel = it;
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                screenStateLiveData.postValue(ScreenState.Loading(false))
                hotelLiveData.postValue(it)
            },{
                screenStateLiveData.postValue(ScreenState.Loading(false))

            })
        compositeDisposable.add(disposable)
    }

}
