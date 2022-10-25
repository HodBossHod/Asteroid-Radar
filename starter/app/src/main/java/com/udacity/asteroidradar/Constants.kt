package com.udacity.asteroidradar

import android.os.Build
import com.udacity.asteroidradar.Constants.API_KEY
import java.text.SimpleDateFormat
import java.util.*

object Constants {
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "https://api.nasa.gov/"
    const val API_KEY = "DEMO_KEY" //TODO:Replace Demo_key by your key here
    //to get the asteroids
    const val ASTEROIDS_PATH="neo/rest/v1/feed"
    //to get the image of the day
    const val PICTURE_OF_THE_DAY_PATH="planetary/apod"



    fun showToday(): String {
        val formattedDate= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat(API_QUERY_DATE_FORMAT,Locale.getDefault())
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        return formattedDate.format(Calendar.getInstance().time)
    }

    fun showAfterWeek(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR,6)
        val currentTime = calendar.time
        val formattedDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat(API_QUERY_DATE_FORMAT, Locale.getDefault())
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        return formattedDate.format(currentTime)
    }
}