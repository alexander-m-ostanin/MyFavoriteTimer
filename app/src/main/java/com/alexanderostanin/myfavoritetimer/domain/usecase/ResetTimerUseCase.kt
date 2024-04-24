package com.alexanderostanin.myfavoritetimer.domain.usecase

import android.app.Application
import android.content.Intent
import com.alexanderostanin.myfavoritetimer.domain.service.TimerService
import com.alexanderostanin.myfavoritetimer.utils.Constants

class ResetTimerUseCase(private val application: Application) {
    suspend fun execute() {
        Intent(application.applicationContext, TimerService::class.java).also {
            it.action = Constants.SERVICE_ACTION_RESET
            application.startService(it)
        }
    }
}