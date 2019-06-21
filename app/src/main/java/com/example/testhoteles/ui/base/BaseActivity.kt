package com.example.testhoteles.ui.base

import android.graphics.PorterDuff
import android.opengl.Visibility
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import com.example.testhoteles.MyApplication
import com.example.testhoteles.R
import com.example.testhoteles.utils.showToastMessage
import kotlinx.android.synthetic.main.toolbar_container.*
import kotlinx.android.synthetic.main.view_container_loading.*

abstract class BaseActivity : AppCompatActivity() {

    fun showLoading(show: Boolean, mainView:View){
        flContainerMainLoading.visibility = if(show) View.VISIBLE else View.GONE
        mainView.visibility = if(!show) View.VISIBLE else View.GONE
        pbLoading.visibility = if(show) View.VISIBLE else View.GONE
        tvPhError.visibility = View.GONE
    }

    fun showErrorPlaceHolder(error: String, mainView: View) {
        mainView.visibility = View.GONE
        flContainerMainLoading.visibility = View.VISIBLE
        tvPhError.visibility = View.VISIBLE
        tvPhError.text = error
        pbLoading.visibility = View.GONE
    }

    fun setUpBackToolBar(@StringRes title: Int) {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = getString(title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.getNavigationIcon()?.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);


    }

    fun showToastMessage(message: String) {
        var toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.view.setBackgroundResource(R.drawable.bg_darker_gray_rectangle_with_radius)
        ((toast.view as LinearLayout).get(0) as TextView).setTextColor(this.resources.getColor(android.R.color.white))
        toast.show()
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}