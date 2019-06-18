package com.example.testhoteles.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
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
    @Inject lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val app = application as? MyApplication
        app?.component?.inject(this)
        setupList()
        observeViewModelData()
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
                pos, hotel ->
                onClickHotel(hotel)

            }
            rvHotels?.adapter?.notifyDataSetChanged()
        })
    }

    private fun onClickHotel(hotel: Hotel) {
        val intent = Intent(this, HotelDetailActivity::class.java)
        intent.putExtra(Constants.HOTEL_EXTRA, hotel)
        startActivity(intent)
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
        //rvHotels.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }
}
