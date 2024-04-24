package com.alexanderostanin.myfavoritetimer.presentation.viewmodel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.alexanderostanin.myfavoritetimer.domain.model.TimerState
import com.alexanderostanin.myfavoritetimer.utils.Constants

class TimerServiceReceiver (private val timerViewModel: TimerViewModel): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        timerViewModel.onTimerStateChanged(enumValueOf<TimerState>(intent?.getStringExtra(Constants.SERVICE_TIMER_STATE).toString()))
        timerViewModel.onTimeLeftChanged(intent?.getLongExtra(Constants.SERVICE_TIME_LEFT, 0)!!)
        timerViewModel.onTimeLeftTextChanged(intent.getStringExtra(Constants.SERVICE_TIME_DURATION_TEXT).toString())
        timerViewModel.onTimerProgressChanged(intent.getFloatExtra(Constants.SERVICE_TIME_PROGRESS, 0f))
    }
}



