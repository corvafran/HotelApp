package com.example.testhoteles.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testhoteles.data.model.Hotel
import com.example.testhoteles.data.repositories.HotelsRepository
import com.example.testhoteles.ui.base.BaseViewModel
import com.example.testhoteles.ui.base.ScreenState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel@Inject constructor(private val repository: HotelsRepository): BaseViewModel(){
    val hotelsLiveData : MutableLiveData<MutableList<Hotel>> = MutableLiveData()
    val screenStateLiveData : MutableLiveData<ScreenState> = MutableLiveData()
    var searchDisposable: Disposable? = null

    init {
        getHotels()
    }

    private fun getHotels(){
        screenStateLiveData.value= ScreenState.Loading(true)
        val disposable = repository.getAllHotels()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccessGetHotels(it)
            }, {
                onErrorGetHotels(it)

            })
        compositeDisposable.add(disposable)
    }

    private fun onSuccessGetHotels(it: MutableList<Hotel>?) {
        screenStateLiveData.value = ScreenState.Loading(false)
        hotelsLiveData.postValue(it)
    }

    private fun onErrorGetHotels(throwable: Throwable) {
        screenStateLiveData.value = ScreenState.Loading(false)
        screenStateLiveData.value = ScreenState.ToastMessage("No se pudieron actualizar los hoteles")
        compositeDisposable.add(repository.getAllHotelsFromDb()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    it -> onSuccessGetHotels(it)
            }, {

            }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
        searchDisposable?.dispose()
    }

    fun attendOnSearchItems(newText: String) {
        searchDisposable?.dispose()
        screenStateLiveData.value = ScreenState.Loading(true)
        searchDisposable = repository.getQueryHotelsFromDb(newText)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    it -> onSuccessGetHotels(it)
            }, {
                screenStateLiveData.value = ScreenState.Loading(false)

            })
    }
}