package com.alexanderostanin.myfavoritetimer.domain.usecase

import com.alexanderostanin.myfavoritetimer.domain.model.Timer
import com.alexanderostanin.myfavoritetimer.domain.repository.TimerRepository

class UpdateTimerUseCase (private val timerRepository: TimerRepository){
    suspend fun execute(timer: Timer){
        return timerRepository.updateTimer(timer)
    }
}