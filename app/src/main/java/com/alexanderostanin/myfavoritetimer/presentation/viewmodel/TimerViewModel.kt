package com.alexanderostanin.myfavoritetimer.presentation.viewmodel

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexanderostanin.myfavoritetimer.domain.model.Timer
import com.alexanderostanin.myfavoritetimer.domain.model.TimerState
import com.alexanderostanin.myfavoritetimer.domain.usecase.CreateTimerUseCase
import com.alexanderostanin.myfavoritetimer.domain.usecase.DeleteTimerUseCase
import com.alexanderostanin.myfavoritetimer.domain.usecase.ReadTimerListUseCase
import com.alexanderostanin.myfavoritetimer.domain.usecase.UpdateTimerUseCase
import com.alexanderostanin.myfavoritetimer.domain.service.TimerService
import com.alexanderostanin.myfavoritetimer.domain.usecase.ResetTimerUseCase
import com.alexanderostanin.myfavoritetimer.domain.usecase.StartTimerUseCase
import com.alexanderostanin.myfavoritetimer.domain.usecase.StopTimerUseCase
import com.alexanderostanin.myfavoritetimer.utils.Constants
import com.alexanderostanin.myfavoritetimer.utils.longToHoursInt
import com.alexanderostanin.myfavoritetimer.utils.longToMinutesInt
import com.alexanderostanin.myfavoritetimer.utils.longToSecondsInt
import com.alexanderostanin.myfavoritetimer.utils.longToTimeString
import com.alexanderostanin.myfavoritetimer.utils.timeStringToLong
import kotlinx.coroutines.launch

class TimerViewModel(
    private val application: Application,
    private val createTimerUseCase: CreateTimerUseCase,
    private val readTimerListUseCase: ReadTimerListUseCase,
    private val updateTimerUseCase: UpdateTimerUseCase,
    private val deleteTimerUseCase: DeleteTimerUseCase,
    private val startTimerUseCase: StartTimerUseCase,
    private val stopTimerUseCase: StopTimerUseCase,
    private val resetTimerUseCase: ResetTimerUseCase
) : ViewModel() {

    private val TAG = "TimerViewModel"

    //==LiveData==

    //List
    private val _timerList = MutableLiveData<List<Timer>>()
    val timerList: LiveData<List<Timer>>
        get() = _timerList

    //TimerDto
    private val _currentTimer = MutableLiveData<Timer>()
    val currentTimer: LiveData<Timer>
        get() = _currentTimer

    //Edit Timer Attributes
    private val _timerName = MutableLiveData<String>()
    val timerName: LiveData<String>
        get() = _timerName

    private val _hours = MutableLiveData<Int>()
    val hours: LiveData<Int>
        get() = _hours

    private val _minutes = MutableLiveData<Int>()
    val minutes: LiveData<Int>
        get() = _minutes

    private val _seconds = MutableLiveData<Int>()
    val seconds: LiveData<Int>
        get() = _seconds

    //CountDown Timer Attributes
    private val _timerState = MutableLiveData<TimerState>()
    val timerState: LiveData<TimerState>
        get() = _timerState


    private val _timeLeft = MutableLiveData<Long>()
    val timeLeft: LiveData<Long>
        get() = _timeLeft


    private val _timeLeftText = MutableLiveData<String>()
    val timeLeftText: LiveData<String>
        get() = _timeLeftText


    private val _progress = MutableLiveData<Float>()
    val progress: LiveData<Float>
        get() = _progress


    //CountDown Timer Progress
    fun onTimerStateChanged(timerState: TimerState){
        _timerState.value = timerState
    }

    fun onTimeLeftChanged(timeLeft: Long){
        _timeLeft.value = timeLeft
    }

    fun onTimeLeftTextChanged(timeLeftText: String){
        _timeLeftText.value = timeLeftText
    }

    fun onTimerProgressChanged(progress: Float){
        _progress.value = progress
    }

    fun buildCurrentTimer(timer: Timer) {
        _currentTimer.postValue(timer)
        _timerName.value = timer.name
        _timeLeft.value = timer.duration
        _hours.value = longToHoursInt(timer.duration)
        _minutes.value = longToMinutesInt(timer.duration)
        _seconds.value = longToSecondsInt(timer.duration)
        _timeLeftText.value = longToTimeString(timeLeft.value!!)
        _progress.value = 0f
        _timerState.value = TimerState.IS_PAUSED

        startService()
    }


    //==Use cases==
    fun getAllTimers() = viewModelScope.launch {
        readTimerListUseCase.execute().collect{
            _timerList.postValue(it)
        }
    }
    fun createTimer(timer: Timer) = viewModelScope.launch {
        createTimerUseCase.execute(timer)
    }
    fun updateTimer(timer: Timer) = viewModelScope.launch {
        updateTimerUseCase.execute(timer)
    }
    fun deleteTimer(timer: Timer) = viewModelScope.launch {
        deleteTimerUseCase.execute(timer)
    }
    fun startTimer() = viewModelScope.launch{
        onTimerStateChanged(TimerState.IS_PLAYING)
        startTimerUseCase.execute(currentTimer.value!!, timeLeft.value!!)
    }
    fun stopTimer() = viewModelScope.launch {
        onTimerStateChanged(TimerState.IS_PAUSED)
        stopTimerUseCase.execute()
    }
    fun resetTimer() = viewModelScope.launch {
        onTimerStateChanged(TimerState.IS_PAUSED)
        resetTimerUseCase.execute()
    }


    //==Service==
    fun startService(){
        Intent(application.applicationContext, TimerService::class.java).also {
            it.action = Constants.SERVICE_ACTION_INIT
            it.putExtra(Constants.SERVICE_TIME_LEFT, timeLeft.value)
//            it.putExtra(Constants.SERVICE_TIME_INITIAL, currentTimer.value?.duration)
            application.startService(it)
        }
    }

    fun stopService(){
        Intent(application.applicationContext, TimerService::class.java).also {
            it.action = Constants.SERVICE_ACTION_STOP
            application.startService(it)
        }
    }


    //==EditTimer==
    fun onNameUpdate(newName: String) {
        _timerName.value = newName
    }

    fun getCountValue(valueType: String): String {
        return when (valueType) {
            Constants.HOURS_TYPE -> StringBuilder().append(if (hours.value!! <10) "0${hours.value}" else hours.value).toString()
            Constants.MINUTES_TYPE -> StringBuilder().append(if (minutes.value!! <10) "0${minutes.value}" else minutes.value).toString()
            Constants.SECONDS_TYPE -> StringBuilder().append(if (seconds.value!! <10) "0${seconds.value}" else seconds.value).toString()
//            HOURS_TYPE -> "h"
//            MINUTES_TYPE -> "m"
//            SECONDS_TYPE -> "s"
            else -> "00"
        }
    }

    fun onValueIncreaseClick(valueType: String) {
        when (valueType) {
            Constants.HOURS_TYPE ->
                if (hours.value!! >= 24 - Constants.HOURS_INTERVAL) _hours.value =
                    0 else _hours.value = hours.value?.plus(Constants.HOURS_INTERVAL)

            Constants.MINUTES_TYPE ->
                if (minutes.value!! >= 60 - Constants.MINUTES_INTERVAL) _minutes.value =
                    0 else _minutes.value = _minutes.value?.plus(Constants.MINUTES_INTERVAL)

            Constants.SECONDS_TYPE ->
                if (seconds.value!! >= 60 - Constants.SECONDS_INTERVAL) _seconds.value =
                    0 else _seconds.value = _seconds.value?.plus(Constants.SECONDS_INTERVAL)

            else -> Log.e(TAG, "onValueIncreaseClick: Unknown valueType $valueType")
        }
    }

    fun onValueDecreaseClick(valueType: String) {
        when (valueType) {
            Constants.HOURS_TYPE ->
                if (hours.value!! <= 0) _hours.value =
                    24 - Constants.HOURS_INTERVAL else _hours.value = _hours.value?.minus(Constants.HOURS_INTERVAL)

            Constants.MINUTES_TYPE ->
                if (minutes.value!! <= 0) _minutes.value =
                    60 - Constants.MINUTES_INTERVAL else _minutes.value = minutes.value!! - Constants.MINUTES_INTERVAL

            Constants.SECONDS_TYPE ->
                if (seconds.value!! <= 0) _seconds.value =
                    60 - Constants.SECONDS_INTERVAL else _seconds.value = _seconds.value?.minus(
                    Constants.SECONDS_INTERVAL
                )

            else -> Log.e(TAG, "onValueDecreaseClick: Unknown valueType $valueType")
        }
    }

    fun onSaveChanges(){
        val timer = Timer(
            currentTimer.value!!.id,
            timerName.value!!,
            timeStringToLong(hours.value!!, minutes.value!!, seconds.value!!)
        )
        buildCurrentTimer(timer)
        if (timer.id == 0) createTimer(timer)
        else updateTimer(timer)
    }

}