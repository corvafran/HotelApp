package com.example.testhoteles.utils

import com.example.testhoteles.R

object AmenitiesIconUtils {
    const val PARKING_ID = "PARKING"
    const val PISCN_ID = "PISCN"
    const val BREAKFST_ID = "BREAKFST"
    const val WIFI_ID = "WIFI"

    fun getDrawableFromId(id: String): Int= when(id){
        PARKING_ID -> R.drawable.ic_car_in_garage
        PISCN_ID -> R.drawable.ic_swimming_pool
        WIFI_ID -> R.drawable.ic_wifi_connection
        BREAKFST_ID -> R.drawable.ic_breakfast
        else -> 0
        }

}