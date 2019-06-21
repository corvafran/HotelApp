package com.example.testhoteles.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testhoteles.data.model.Hotel
import com.example.testhoteles.data.repositories.HotelsRepository
import com.example.testhoteles.ui.base.BaseViewModel
import com.example.testhoteles.ui.base.ScreenState
import com.example.testhoteles.utils.EspressoIdlingResource
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class MainViewModel@Inject constructor(private val repository: HotelsRepository,
                                       @Named("newThread") private val newScheduler: Scheduler,
                                       @Named("mainThread") private val androidScheduler: Scheduler
): BaseViewModel(){
    val hotelsLiveData : MutableLiveData<MutableList<Hotel>> = MutableLiveData()
    val screenStateLiveData : MutableLiveData<ScreenState> = MutableLiveData()
    var searchDisposable: Disposable? = null

    init {
        getHotels()
    }

    private fun getHotels(){
        EspressoIdlingResource.increment(); // App is busy until further notice

        screenStateLiveData.value= ScreenState.Loading(true)
        val disposable = repository.getAllHotels()
            .subscribeOn(newScheduler)
            .observeOn(androidScheduler)
            .subscribe({
                onSuccessGetHotels(it)
            }, {
                onErrorGetHotels(it)

            })
        compositeDisposable.add(disposable)
    }

    private fun onSuccessGetHotels(it: MutableList<Hotel>?) {
        screenStateLiveData.value = ScreenState.Loading(false)
        if(!it.isNullOrEmpty()){
        hotelsLiveData.postValue(it)
        }else{
            screenStateLiveData.value = ScreenState.Error("No se encontraron hoteles")
        }
        EspressoIdlingResource.decrement()
    }

    private fun onErrorGetHotels(throwable: Throwable) {
        screenStateLiveData.value = ScreenState.Loading(false)
        screenStateLiveData.value = ScreenState.ToastMessage("No se pudieron actualizar los hoteles")
        compositeDisposable.add(repository.getAllHotelsFromDb()
            .subscribeOn(newScheduler)
            .observeOn(androidScheduler)
            .subscribe({
                    it -> onSuccessGetHotels(it)
            }, {
                screenStateLiveData.value = ScreenState.Error("Ocurrio un error, vuelva a intentar")
                EspressoIdlingResource.decrement()
            }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
        searchDisposable?.dispose()
    }

    fun attendOnSearchItems(newText: String) {
        EspressoIdlingResource.increment()
        searchDisposable?.dispose()
        screenStateLiveData.value = ScreenState.Loading(true)
        searchDisposable = repository.getQueryHotelsFromDb(newText)
            .subscribeOn(newScheduler)
            .observeOn(androidScheduler)
            .subscribe({
                    it -> onSuccessGetHotels(it)
            }, {
                screenStateLiveData.value = ScreenState.Loading(false)
                EspressoIdlingResource.decrement()

            })
    }
}