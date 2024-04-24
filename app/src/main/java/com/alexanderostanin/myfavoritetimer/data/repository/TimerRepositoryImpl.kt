package com.alexanderostanin.myfavoritetimer.data.repository

import com.alexanderostanin.myfavoritetimer.data.db.TimerDao
import com.alexanderostanin.myfavoritetimer.domain.model.Timer
import com.alexanderostanin.myfavoritetimer.domain.repository.TimerRepository
import kotlinx.coroutines.flow.Flow


class TimerRepositoryImpl(private val dao: TimerDao): TimerRepository {

    override suspend fun getTimerList(): Flow<MutableList<Timer>> =
        dao.getAllTimers()

    override suspend fun updateTimer(timer: Timer) =
        dao.updateTimer(timer)

    override suspend fun createTimer(timer: Timer) =
        dao.insertTimer(timer)

    override suspend fun deleteTimer(timer: Timer) =
        dao.deleteTimer(timer)

}