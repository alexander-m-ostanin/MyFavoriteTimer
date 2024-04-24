package com.alexanderostanin.myfavoritetimer.domain.usecase

import android.app.Application
import android.content.Intent
import com.alexanderostanin.myfavoritetimer.domain.model.Timer
import com.alexanderostanin.myfavoritetimer.domain.service.TimerService
import com.alexanderostanin.myfavoritetimer.utils.Constants

class StartTimerUseCase (private val application: Application) {
    suspend fun execute(timer: Timer, timeLeft: Long){
        Intent(application.applicationContext, TimerService::class.java).also {
            it.action = Constants.SERVICE_ACTION_START
            it.putExtra(Constants.SERVICE_TIME_LEFT, timeLeft)
//            it.putExtra(Constants.SERVICE_TIME_INITIAL, timer.duration)
            application.startService(it)
        }
    }

}