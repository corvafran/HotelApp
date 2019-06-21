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
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import com.example.testhoteles.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import java.util.concurrent.atomic.AtomicBoolean


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource())
    }

    @Test
    fun onClickRvItem(){
        onView(withId(R.id.rvHotels))
            .perform(actionOnItemAtPosition<HotelsAdapter.HotelViewHolder>(0,click()))
    }

    @After fun  unregisterIdle(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource())
    }
}
