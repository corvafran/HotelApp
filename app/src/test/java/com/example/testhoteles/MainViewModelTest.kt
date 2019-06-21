package com.example.testhoteles

import android.app.Application

import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.testhoteles.data.model.Hotel
import com.example.testhoteles.data.remote.ApiServices
import com.example.testhoteles.data.repositories.HotelsRepository
import com.example.testhoteles.local.db.HotelDao
import com.example.testhoteles.ui.base.ScreenState
import com.example.testhoteles.ui.main.MainViewModel

import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*

class MainViewModelTest {

    @get:Rule
    var instantExecuterRule = InstantTaskExecutorRule()
    @Mock
    lateinit var hotelDao: HotelDao


    @Mock
    lateinit var observer: Observer<MutableList<Hotel>>

    @Mock
    private lateinit var hotelsRepository: HotelsRepository

    @Mock
    private lateinit var context: Application

    @Mock
    private lateinit var apiServices: ApiServices

    private lateinit var mainViewModel: MainViewModel

    private lateinit var testSchedulers: TestScheduler
    @Mock
    private lateinit var lifecycleOwner: LifecycleOwner

    @Mock
    private lateinit var lifecycle: Lifecycle

    val hotels = mutableListOf<Hotel>()

    @Before
    fun setupMainViewModel() {
        MockitoAnnotations.initMocks(this)
        hotels.addAll(TestData.getTestHotel())
        setupContext()
        setupHotelsDao()
        //hotelsRepository = HotelsRepository(apiServices, hotelDao)
        doReturn(Single.just(hotels)).`when`(hotelsRepository).getAllHotels()
        testSchedulers= TestScheduler()
        mainViewModel = MainViewModel(hotelsRepository,  testSchedulers, testSchedulers)
        mainViewModel.hotelsLiveData.observeForever(observer)
    }

    private fun setupContext() {
        Mockito.`when`<Context>(context.applicationContext).thenReturn(context)

        Mockito.`when`(context.resources).thenReturn(Mockito.mock(Resources::class.java))

        whenever(lifecycle.currentState).thenReturn(Lifecycle.State.RESUMED)
        whenever(lifecycleOwner.lifecycle).thenReturn(lifecycle)

    }


    fun setupHotelsDao() {
        whenever(hotelDao.getHotels()).thenReturn(Single.just(mutableListOf()))
        whenever(hotelDao.getQueryHotels(any())).thenReturn(Single.just(mutableListOf()))
    }

    @Test
    fun searchInput_ChangesKeywordAndSearchDb() {
        doReturn(Single.just(hotels)).`when`(hotelsRepository).getQueryHotelsFromDb("")
        with(mainViewModel) {
            val stateLoading = ScreenState.Loading(true)
            screenStateLiveData.postValue(stateLoading)
            attendOnSearchItems("")
            //Verifico que se inicio el loading
            if(screenStateLiveData.value != null)
            assert(screenStateLiveData.value!! is ScreenState.Loading && ((screenStateLiveData.value as ScreenState.Loading).show) == true)
            com.nhaarman.mockitokotlin2.verify(hotelsRepository).getQueryHotelsFromDb("")
            testSchedulers.triggerActions()
            //Verifico que se oculte el loading
            assert(screenStateLiveData.value!! is ScreenState.Loading && ((screenStateLiveData.value as ScreenState.Loading).show) == false)
            //Verifico que se hayan enviado los hoteles en liveData
            assert(hotelsLiveData.value!!.size == hotels.size)
        }
    }

}