package com.example.testhoteles.ui.hotelDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testhoteles.GlideApp
import com.example.testhoteles.MyApplication
import com.example.testhoteles.R
import com.example.testhoteles.data.model.Amenity
import com.example.testhoteles.data.model.Hotel
import com.example.testhoteles.ui.base.BaseActivity
import com.example.testhoteles.ui.base.ScreenState
import com.example.testhoteles.ui.main.MainViewModel
import com.example.testhoteles.ui.photoDetail.PhotoDetailActivity
import com.example.testhoteles.ui.reviews.ReviewsActivity
import com.example.testhoteles.utils.Constants
import kotlinx.android.synthetic.main.activity_hotel_detail.*
import kotlinx.android.synthetic.main.item_amenitie.*
import javax.inject.Inject


class HotelDetailActivity: BaseActivity() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var hotelDetailViewModel: HotelDetailViewModel

    companion object{
        fun start(hotel: Hotel, context: Context){
            val intent = Intent(context, HotelDetailActivity::class.java)
            intent.putExtra(Constants.HOTEL_EXTRA, hotel)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.testhoteles.R.layout.activity_hotel_detail)
        setUpBackToolBar(R.string.toolbar_title_hotel_detail)
        val app = application as? MyApplication
        val hotelExtra: Hotel = getHotelExtra()
        setupAmenitiesList()
        loadHotelInfo(hotelExtra)
        app?.component?.inject(this)
        hotelDetailViewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(HotelDetailViewModel::class.java)
        observeViewModelData()
        hotelDetailViewModel.start(hotelExtra)
        setEvents()
    }

    private fun setEvents() {
        llReviews.setOnClickListener {
            if(!hotelDetailViewModel.hotel!!.reviews.isNullOrEmpty()){
                ReviewsActivity.start(hotelDetailViewModel.hotel!!.reviews, this)
            }
        }
        ivHotel.setOnClickListener {
            PhotoDetailActivity.start(hotelDetailViewModel.hotel?.mainPicture, this)
        }
    }

    private fun loadHotelInfo(hotel: Hotel) {
        GlideApp.with(this)
            .load(hotel.mainPicture)
            .placeholder(R.drawable.ph_hotel)
            .into(ivHotel)
        if(hotel.stars != null) rbHotelStars.rating = hotel.stars!!
        tvAddress.setText(hotel.address)
        tvName.setText(hotel.name)
        var currencyMask = if (hotel.price != null && hotel.price!!.currency != null )hotel.price!!.currency!!.mask else "$"
        var priceMin = hotel.price!!.base
        var priceMax = hotel.price!!.best
        tvPrice.setText(String.format(getString(R.string.item_hotel_price_min_max),
            currencyMask, priceMin, currencyMask, priceMax))
        if(hotel.rating != null) tvRating.setText("%.2f".format(hotel.rating))
        tvCommentsCounter.setText(getString(R.string.hotel_detail_see_comments, if(hotel.reviews != null) hotel.reviews!!.size else 0))
        if(hotel.description != null)tvDescription.setText(hotel.description)
        loadAmenities(hotel.amenities)
        tvLocality.text = ("${hotel.city?.name}, ${hotel.city?.country?.name}")
    }

    private fun loadAmenities(amenities: List<Amenity?>?) {
        if (amenities.isNullOrEmpty()){
            tvTitleAmenities.visibility = View.GONE
            rvAmenities.visibility = View.GONE
        }else {
            (rvAmenities.adapter as AmenitiesAdapter).list = amenities
            rvAmenities?.adapter?.notifyDataSetChanged()
        }
    }

    private fun observeViewModelData() {
        hotelDetailViewModel.screenStateLiveData.observe(this, Observer<ScreenState> {
                screenState: ScreenState? ->
            onScreenStateChanged(screenState)
        })
        hotelDetailViewModel.hotelLiveData.observe(this, Observer {
                it -> loadHotelInfo(it)
        })
    }

    private fun onScreenStateChanged(screenState: ScreenState?) {
        when (screenState) {
            is ScreenState.Error -> {

            }
            is ScreenState.Loading -> {
                showLoading(screenState.show, llContainerInfoDetail);
            }
        }
    }

    private fun setupAmenitiesList() {
        rvAmenities.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        rvAmenities.adapter = AmenitiesAdapter()
    }

    fun getHotelExtra(): Hotel {
        return intent.extras.getParcelable(Constants.HOTEL_EXTRA)
    }
}