package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.Constants.showAfterWeek
import com.udacity.asteroidradar.Constants.showToday
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.AsteroidApi
import com.udacity.asteroidradar.asteroiddatabase.getSingleInstance
import com.udacity.asteroidradar.repository.Repository
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getSingleInstance(application)
    private val repository = Repository(database)

    private val databaseDao= getSingleInstance(application).asteroidDao

    private val _status=MutableLiveData<String>()
    val status
        get() = _status

    private val _navigateToAsteroidDetail = MutableLiveData<Asteroid?>()
    val navigateToAsteroidDetail:LiveData<Asteroid?>
        get() = _navigateToAsteroidDetail

    private val _allAsteroids = MutableLiveData<List<Asteroid>>()
    val allAsteroids : LiveData<List<Asteroid>>
        get() = _allAsteroids


    private val _imageOfTheDay = MutableLiveData<PictureOfDay>()
    val imageOfTheDay: LiveData<PictureOfDay>
        get() = _imageOfTheDay

    init {
        getSavedAsteroids()
        getAsteroidRadarProperties()
        getImageOfTheDay()
    }


    private fun getAsteroidRadarProperties() {
        viewModelScope.launch {
            try {
                repository.refreshAsteroids()
            } catch (exception: Exception) {
                _status.value = "Failure: ${exception.message}"
            }
        }
    }

    private fun getImageOfTheDay() {
        viewModelScope.launch {
            try {
                _imageOfTheDay.value = AsteroidApi.getImageOfTheDay()
            } catch (exception: Exception) {
                _status.value = "Image of the day loading failure: ${exception.message}"
            }
        }
    }

    fun getWeekAsteroids() {
        viewModelScope.launch {
            database.asteroidDao.getFullWeek(showToday(), showAfterWeek()).collect{
                _allAsteroids.value=it
            }
        }
    }

    fun getTodayAsteroids() {
        viewModelScope.launch {
            database.asteroidDao.getFullWeek(showToday(), showToday()).collect {
                _allAsteroids.value = it
            }
        }
    }

    fun getSavedAsteroids() {
        viewModelScope.launch {
            database.asteroidDao.getAllAsteroids().collect {
                _allAsteroids.value = it
            }
        }
    }

    fun showAsteroidDetails(asteroid: Asteroid) {
        _navigateToAsteroidDetail.value = asteroid
    }
    fun finishNav() {
        _navigateToAsteroidDetail.value = null
    }

}