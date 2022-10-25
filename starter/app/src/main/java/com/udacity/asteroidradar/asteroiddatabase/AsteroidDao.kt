package com.udacity.asteroidradar.asteroiddatabase


import androidx.room.*
import com.udacity.asteroidradar.Asteroid
import kotlinx.coroutines.flow.Flow


@Dao
interface AsteroidDao {

    @Query("SELECT * from asteroidTable ORDER by close_approach_date")
    fun getAllAsteroids(): Flow<List<Asteroid>>


    @Query("SELECT * from asteroidTable where close_approach_date>= :startDate and close_approach_date<=:endDate ORDER by close_approach_date")
    fun getFullWeek(startDate :String,endDate:String): Flow<List<Asteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAsteroidList(asteroids: List<Asteroid>)

}