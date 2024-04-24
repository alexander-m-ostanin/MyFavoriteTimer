package com.alexanderostanin.myfavoritetimer.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alexanderostanin.myfavoritetimer.domain.model.Timer
import com.alexanderostanin.myfavoritetimer.utils.Constants.TIMER_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface TimerDao {

    @Query("select * from $TIMER_TABLE_NAME order by name")
    fun getAllTimers(): Flow<MutableList<Timer>>
//    fun getAllTimers(): List<Timer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTimer(timer: Timer)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTimer(timer: Timer)

    @Delete
    fun deleteTimer(timer: Timer)

}