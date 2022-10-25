package com.udacity.asteroidradar.asteroiddatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.Asteroid


@Database(entities = [Asteroid::class], version = 1, exportSchema = false)
abstract class AsteroidDatabase:RoomDatabase() {
    abstract val asteroidDao: AsteroidDao
}

private lateinit var SINGLE_INSTANCE: AsteroidDatabase
fun getSingleInstance(context: Context): AsteroidDatabase {
    synchronized(AsteroidDatabase::class.java) {
        // If instance is `null` make a new database instance.
        if (!::SINGLE_INSTANCE.isInitialized) {
            SINGLE_INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AsteroidDatabase::class.java,
                "asteroid_db"
            ).build()
        }
        return SINGLE_INSTANCE
    }
}

