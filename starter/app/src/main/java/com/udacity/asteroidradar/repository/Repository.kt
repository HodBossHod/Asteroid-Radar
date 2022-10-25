package com.udacity.asteroidradar.repository

import com.udacity.asteroidradar.api.AsteroidApi
import com.udacity.asteroidradar.asteroiddatabase.AsteroidDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val database: AsteroidDatabase) {


    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            val asteroidlist = AsteroidApi.getAsteroidsList("","")
            database.asteroidDao.insertAsteroidList(asteroidlist)
        }
    }




}