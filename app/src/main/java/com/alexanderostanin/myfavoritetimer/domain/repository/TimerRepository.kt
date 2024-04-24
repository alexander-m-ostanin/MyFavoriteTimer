package com.alexanderostanin.myfavoritetimer.domain.repository

import com.alexanderostanin.myfavoritetimer.domain.model.Timer
import kotlinx.coroutines.flow.Flow

interface TimerRepository {
//    fun getTimerList(): List<Timer>
//    fun getTimerById(id: Int): Timer
//    fun updateTimer(timer: Timer): CallbackMessage
//    fun createTimer(timer: Timer): CallbackMessage
//    fun deleteTimer(timer: Timer): CallbackMessage


    suspend fun getTimerList(): Flow<MutableList<Timer>>
//    suspend fun getTimerById(id: Int): Timer
    suspend fun updateTimer(timer: Timer)
    suspend fun createTimer(timer: Timer)
    suspend fun deleteTimer(timer: Timer)
}