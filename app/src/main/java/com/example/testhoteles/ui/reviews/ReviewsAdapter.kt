package com.example.testhoteles.ui.reviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testhoteles.R
import com.example.testhoteles.data.model.Amenity
import com.example.testhoteles.data.model.Review
import com.example.testhoteles.ui.base.BaseViewHolder
import com.example.testhoteles.ui.hotelDetail.AmenitiesAdapter

class ReviewsAdapter: RecyclerView.Adapter<BaseViewHolder<Review?>>()  {
    var reviews: ArrayList<Review>? = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Review?> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reviews!!.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Review?>, position: Int) {
        holder.bindItem(reviews!![position])
    }

    class ReviewViewHolder(itemView: View): BaseViewHolder<Review?>(itemView){
        var tvUser: TextView? = null
        var tvGoodComment: TextView? = null
        var tvBadComment: TextView? = null

        init {
            tvUser = itemView.findViewById(R.id.tvUser)
            tvGoodComment = itemView.findViewById(R.id.tvGoodComment)
            tvBadComment = itemView.findViewById(R.id.tvBadComment)
        }

        override fun bindItem(item: Review?) {
            tvUser?.text = "${item?.user?.name} (${item?.user?.country})"
            tvBadComment?.text = itemView.context.getString(R.string.bad_comment,
                if (!item?.comments?.bad.isNullOrEmpty()) item?.comments?.bad else "-")
            tvGoodComment?.text = itemView.context.getString(R.string.good_comment,
                if (!item?.comments?.good.isNullOrEmpty()) item?.comments?.good else "-")
        }

    }
}