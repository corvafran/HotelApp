package com.example.testhoteles.ui.photoDetail

import android.os.Bundle
import com.example.testhoteles.GlideApp
import com.example.testhoteles.R
import com.example.testhoteles.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_photo.*

class PhotoDetailActivity: BaseActivity(){
    companion object{
        const val PHOTO_EXTRA: String = "photo_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        val urlPhoto: String? = intent.getStringExtra(PHOTO_EXTRA)
        GlideApp.with(this)
            .load(urlPhoto)
            .placeholder(R.drawable.ph_hotel)
            .into(photoView)
    }
}