package com.example.testhoteles.ui.main

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.testhoteles.MyApplication
import com.example.testhoteles.R
import com.example.testhoteles.data.model.Hotel
import com.example.testhoteles.ui.base.BaseActivity
import com.example.testhoteles.ui.base.ScreenState
import com.example.testhoteles.ui.hotelDetail.HotelDetailActivity
import com.example.testhoteles.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val app = application as? MyApplication
        app?.component?.inject(this)
        ViewModelProvider.NewInstanceFactory()
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(MainViewModel::class.java)
        setupList()
        observeViewModelData()
        setupSearch()
    }

    private fun setupSearch() {
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.attendOnSearchItems(newText!!)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

        })
    }


    private fun observeViewModelData() {
        viewModel.screenStateLiveData.observe(this, Observer<ScreenState> {
            screenState: ScreenState? ->
            onScreenStateChanged(screenState)
        })
        viewModel.hotelsLiveData.observe(this, Observer {
            it ->
            (rvHotels.adapter as HotelsAdapter).hotelsList = it
            (rvHotels.adapter as HotelsAdapter).onClickHotel = {
                pos, hotel, ivHotel ->
                onClickHotel(hotel, ivHotel)

            }
            rvHotels?.adapter?.notifyDataSetChanged()
        })
    }

    private fun onClickHotel(hotel: Hotel, ivHotel: ImageView) {
        HotelDetailActivity.start(hotel, ivHotel, this)
    }

    private fun onScreenStateChanged(screenState: ScreenState?) {
        when (screenState) {
            is ScreenState.Error -> {

            }
            is ScreenState.Loading -> {
                showLoading(screenState.show, rvHotels);
            }
            is ScreenState.ToastMessage -> {
                showToastMessage(screenState.message);
            }
        }
    }


    private fun setupList() {
        rvHotels.adapter = HotelsAdapter()
    }
}
