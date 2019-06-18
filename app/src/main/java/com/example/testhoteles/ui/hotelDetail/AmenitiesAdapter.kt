package com.example.testhoteles.ui.hotelDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testhoteles.GlideApp
import com.example.testhoteles.R
import com.example.testhoteles.data.model.Amenity
import com.example.testhoteles.ui.base.BaseViewHolder

class AmenitiesAdapter: RecyclerView.Adapter<BaseViewHolder<Amenity?>>() {
    var list: List<Amenity?> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Amenity?> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_amenitie, parent, false)
        return AmenityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Amenity?>, position: Int) {
        holder.bindItem(list[position])
    }

    class AmenityViewHolder(itemView: View): BaseViewHolder<Amenity?>(itemView){
        var ivIcon: ImageView? = null
        var tvAmenitie: TextView? = null

        init {
            ivIcon = itemView.findViewById(R.id.ivIcon)
            tvAmenitie = itemView.findViewById(R.id.tvAmenitie)
        }

        override fun bindItem(item: Amenity?) {
            tvAmenitie?.setText(item?.description)
        }

    }
}