package com.example.testhoteles.utils

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import com.example.testhoteles.R


fun Context.showToastMessage(message: String) {
    var toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast.view.setBackgroundResource(R.drawable.bg_darker_gray_rectangle_with_radius)
    (toast.view as TextView).setTextColor(this.resources.getColor(android.R.color.white))
    toast.show()
}


