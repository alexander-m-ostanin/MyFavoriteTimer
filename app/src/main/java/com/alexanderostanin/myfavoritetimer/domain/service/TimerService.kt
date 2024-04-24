package com.alexanderostanin.myfavoritetimer.domain.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.alexanderostanin.myfavoritetimer.R
import com.alexanderostanin.myfavoritetimer.domain.model.TimerState
import com.alexanderostanin.myfavoritetimer.utils.Constants
import com.alexanderostanin.myfavoritetimer.utils.longToTimeString


class TimerService : Service() {

    //==Timer==
    private var countDownTimer: CountDownTimer? = null
    var initialTotalTime by mutableLongStateOf(0)
    val countDownInterval = 1000L // 1 seconds is the lowest
    var timeLeft by mutableLongStateOf(0)
    var timerDurationText by mutableStateOf("")
    var progress by mutableFloatStateOf(0f)
    var timerState by mutableStateOf(TimerState.IS_PAUSED)

    //==Signal==
    private lateinit var mediaPlayer: MediaPlayer

    //==Binder==
    override fun onBind(intent: Intent?): IBinder? = null

    //==Life cycle==
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Constants.SERVICE_ACTION_INIT -> {
                initialTotalTime = intent.getLongExtra(Constants.SERVICE_TIME_LEFT, 0)
            }
            Constants.SERVICE_ACTION_START -> {
                timeLeft = intent.getLongExtra(Constants.SERVICE_TIME_LEFT, 0)
                startCountDownTimer()
            }
            Constants.SERVICE_ACTION_PAUSE -> stopCountDownTimer()
            Constants.SERVICE_ACTION_RESET -> resetCountDownTimer()
            Constants.SERVICE_ACTION_STOP -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        if (this::mediaPlayer.isInitialized){
            mediaPlayer.stop()
            mediaPlayer.release()
        }
        stopSelf()
        super.onDestroy()
        Log.d("TAG", "onDestroy: ")
    }


    //==Timer==
    private fun startCountDownTimer() {
        timerState = TimerState.IS_PLAYING

        countDownTimer = object : CountDownTimer(timeLeft, countDownInterval) {

            override fun onTick(currentTimeLeft: Long) {
                timerDurationText = longToTimeString(currentTimeLeft)
                timeLeft = currentTimeLeft
                progress = currentTimeLeft.toFloat() / initialTotalTime
                sendTimerBroadcast()
            }

            override fun onFinish() {
                timerState = TimerState.IS_RINGING
                timerDurationText = longToTimeString(initialTotalTime)
                progress = 0f
                timeLeft = initialTotalTime
                sendTimerBroadcast()
                playMedia()
            }
        }.start()
    }

    private fun stopCountDownTimer() {
        timerState = TimerState.IS_PAUSED
        countDownTimer?.cancel()
        sendTimerBroadcast()
    }

    private fun resetCountDownTimer() {
        timerState = TimerState.IS_PAUSED
        countDownTimer?.cancel()
        timerDurationText = longToTimeString(initialTotalTime)
        timeLeft = initialTotalTime
        progress = 0f
        sendTimerBroadcast()
        pauseMedia()
    }

    //==Broadcast==
    fun sendTimerBroadcast(){
        Intent(Constants.SERVICE_BROADCAST_INTENT).also {
            it.putExtra(Constants.SERVICE_TIMER_STATE, timerState.toString())
            it.putExtra(Constants.SERVICE_TIME_LEFT, timeLeft)
            it.putExtra(Constants.SERVICE_TIME_DURATION_TEXT, timerDurationText)
            it.putExtra(Constants.SERVICE_TIME_PROGRESS, progress)
            sendBroadcast(it)
        }
    }

    //==Signal==
    private fun playMedia(){
        if (!this::mediaPlayer.isInitialized){
            mediaPlayer = MediaPlayer.create(application.applicationContext, R.raw.signal)
        }
        if (mediaPlayer.isPlaying){
            mediaPlayer.pause()
            mediaPlayer.seekTo(0)
        }
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener { playMedia() }
    }

    private fun pauseMedia(){
        if (!this::mediaPlayer.isInitialized){
            return
        }
        if (mediaPlayer.isPlaying){
            mediaPlayer.pause()
            mediaPlayer.seekTo(0)
        }
    }



}