package com.example.testhoteles.ui.hotelDetail

import com.example.testhoteles.data.model.Review


sealed class Events {
    class OnClickReviews(val reviews: ArrayList<Review?>) : Events()
    class OnClickHotelPhoto(val photoUrl: String?) : Events()
}
