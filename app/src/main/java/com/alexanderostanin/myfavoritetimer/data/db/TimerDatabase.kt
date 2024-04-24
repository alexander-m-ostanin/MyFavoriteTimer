package com.alexanderostanin.myfavoritetimer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexanderostanin.myfavoritetimer.domain.model.Timer

@Database(
    entities = [Timer::class],
    version = 1,
    exportSchema = false
)
abstract class TimerDatabase: RoomDatabase() {

    abstract fun getTimerDao(): TimerDao

}