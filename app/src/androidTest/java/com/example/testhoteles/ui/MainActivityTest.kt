package com.example.testhoteles.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import com.example.testhoteles.R
import com.example.testhoteles.ui.hotelDetail.HotelDetailActivity
import com.example.testhoteles.ui.main.HotelsAdapter
import com.example.testhoteles.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import android.app.Activity
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.testhoteles.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import java.util.concurrent.atomic.AtomicBoolean
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.net.ConnectivityManager
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import com.example.testhoteles.MyApplication
import com.example.testhoteles.data.repositories.HotelsRepository
import com.example.testhoteles.db.DbTestData
import com.example.testhoteles.di.DaggerAppComponent
import com.example.testhoteles.local.db.HotelDao
import com.example.testhoteles.local.db.HotelDatabase
import com.example.testhoteles.ui.base.ScreenState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val mActivityRule = ActivityTestRule(MainActivity::class.java)
    @Inject lateinit var repository: HotelsRepository

    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource())
    }

    @Test
    fun onClickRvItem(){
        if(getRVcount() > 0)
        onView(withId(R.id.rvHotels))
            .perform(actionOnItemAtPosition<HotelsAdapter.HotelViewHolder>(0,click()))

    }

    private fun getRVcount() : Int{
    var recyclerView = mActivityRule.getActivity().findViewById(R.id.rvHotels) as RecyclerView;
    return recyclerView.getAdapter()!!.getItemCount();
}

    @Test
    fun onSearchItem(){
        onView(withId(R.id.searchView)).perform(click());
        onView(withId(R.id.searchView)).perform(typeText("sheraton"));
        if(isConnected(mActivityRule.activity)){
            check(getRVcount() > 0)
            onView(withId(R.id.tvPhError)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        }else{
            if(getRVcount() == 0){
                onView(withId(R.id.tvPhError)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
            }else{
                onView(withId(R.id.tvPhError)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
                check(getRVcount() > 0)
            }
        }
    }

    fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
    @After fun  unregisterIdle(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource())
    }
}
