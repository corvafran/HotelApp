package com.example.testhoteles.ui.main

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testhoteles.GlideApp
import com.example.testhoteles.R
import com.example.testhoteles.data.model.Hotel
import com.example.testhoteles.ui.base.BaseViewHolder

class HotelsAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {
    var hotelsList : MutableList<Hotel> = arrayListOf()
    var lastPosition: Int = -1

    var onClickHotel: (pos: Int, hotel: Hotel, ivHotel: ImageView)-> Unit = {
        pos, hotel, ivHotel ->
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hotel, parent, false)
        return HotelViewHolder(view, onClickHotel)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        (holder as HotelViewHolder).bindItem(hotelsList[position])
        setAnimation(holder.itemView, position)
    }

    override fun getItemCount(): Int = hotelsList.size

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<*>) {
        super.onViewDetachedFromWindow(holder)
        (holder as HotelViewHolder).clearAnimation()

    }
    class HotelViewHolder(itemView : View,  var onClickHotel: (pos: Int, hotel: Hotel, ivHotel: ImageView)-> Unit) : BaseViewHolder<Hotel>(itemView){
        val ivHotel: ImageView = itemView.findViewById(R.id.ivHotel)
        val tvAddress: TextView = itemView.findViewById(R.id.tvAddress)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val rbHotelStars: RatingBar = itemView.findViewById(R.id.rbHotelStars)

        override fun bindItem(item: Hotel) {
            itemView.setOnClickListener {
                onClickHotel(adapterPosition, item,ivHotel)
            }
            GlideApp.with(itemView.context)
                .load(item.mainPicture)
                .placeholder(R.drawable.ph_hotel)
                .into(ivHotel)
            if(item.stars != null)
            rbHotelStars.rating = item.stars!!
            tvAddress.setText(item.address)
            tvName.setText(item.name)
            var currencyMask = if (item.price != null && item.price!!.currency != null )item.price!!.currency!!.mask else "$"
            var priceMin = item.price!!.base
            var priceMax = item.price!!.best
            tvPrice.setText(String.format(itemView.context.getString(R.string.item_hotel_price_min_max),
                currencyMask, priceMin, currencyMask, priceMax))
        }

        fun clearAnimation() {
            itemView.clearAnimation()
        }

    }

    fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            animation.setInterpolator(OvershootInterpolator(2f))
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }
}