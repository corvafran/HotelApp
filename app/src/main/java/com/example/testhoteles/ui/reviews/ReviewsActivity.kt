package com.example.testhoteles.ui.reviews

import android.os.Bundle
import android.os.PersistableBundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testhoteles.R
import com.example.testhoteles.data.model.Hotel
import com.example.testhoteles.data.model.Review
import com.example.testhoteles.ui.base.BaseActivity
import com.example.testhoteles.utils.Constants
import kotlinx.android.synthetic.main.activity_list.*

class ReviewsActivity: BaseActivity(){
    var reviews: ArrayList<Review>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setUpBackToolBar(R.string.toolbar_title_comment)
        reviews = intent.getParcelableArrayListExtra(Constants.REVIEWS_EXTRA)
        setupList()
        showLoading(false, rvList)
        loadReviews(reviews!!)
    }

    private fun setupList() {
        rvList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvList.adapter = ReviewsAdapter()
        rvList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    fun loadReviews(reviews: ArrayList<Review>){
        (rvList.adapter as ReviewsAdapter).reviews = reviews
        rvList.adapter?.notifyDataSetChanged()
    }
}