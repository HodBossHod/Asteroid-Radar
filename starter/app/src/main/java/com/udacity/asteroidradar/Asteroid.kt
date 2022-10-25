package com.udacity.asteroidradar

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "asteroidTable")
@Parcelize
@JsonClass(generateAdapter = true)
data class Asteroid(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "codename")
    val codename: String,
    @ColumnInfo(name = "close_approach_date")
    val closeApproachDate: String,
    @ColumnInfo(name = "absoluteMagnitude")
    val absoluteMagnitude: Double,
    @ColumnInfo(name = "estimatedDiameter")
    val estimatedDiameter: Double,
    @ColumnInfo(name = "relative_velocity")
    val relativeVelocity: Double,
    @ColumnInfo(name = "distance_from_earth")
    val distanceFromEarth: Double,
    @ColumnInfo(name = "is_potentially_hazardous")
    val isPotentiallyHazardous: Boolean
//    @PrimaryKey
//    val id: Long,
//    @ColumnInfo(name = "codename")
//    val codename: String,
//    @ColumnInfo(name = "close_approach_date")
//    val closeApproachDate: String,
//    @ColumnInfo(name = "absoluteMagnitude")
//    val absoluteMagnitude: Double,
//    @ColumnInfo(name = "estimatedDiameter")
//    val estimatedDiameter: Double,
//    @ColumnInfo(name = "relative_velocity")
//    val relativeVelocity: Double,
//    @ColumnInfo(name = "distance_from_earth")
//    val distanceFromEarth: Double,
//    @ColumnInfo(name = "is_potentially_hazardous")
//    val isPotentiallyHazardous: Boolean
) : Parcelable