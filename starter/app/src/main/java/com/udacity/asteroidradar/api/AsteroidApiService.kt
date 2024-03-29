package com.udacity.asteroidradar.api


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.Constants.ASTEROIDS_PATH
import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.Constants.PICTURE_OF_THE_DAY_PATH
import com.udacity.asteroidradar.PictureOfDay
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface AsteroidApiService {

    @GET(ASTEROIDS_PATH)
    suspend fun getAsteroid(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): String


    @GET(PICTURE_OF_THE_DAY_PATH)
    suspend fun retrievePictureOfDay(@Query("api_key") apiKey: String):PictureOfDay
}

object AsteroidApi {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit=Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()


    private val retrofitService : AsteroidApiService by lazy {
        retrofit.create(AsteroidApiService::class.java)
    }

    suspend fun getAsteroidsList(startDate: String,endDate: String): List<Asteroid> {
        val responseString = retrofitService.getAsteroid(startDate, endDate, API_KEY)
        val responseJsonObject = JSONObject(responseString)
        return parseAsteroidsJsonResult(responseJsonObject)
    }

    suspend fun getImageOfTheDay(): PictureOfDay {
        return retrofitService.retrievePictureOfDay(API_KEY)
    }
}