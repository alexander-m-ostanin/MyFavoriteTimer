package com.alexanderostanin.myfavoritetimer.domain.usecase

import com.alexanderostanin.myfavoritetimer.domain.model.Timer
import com.alexanderostanin.myfavoritetimer.domain.repository.TimerRepository
import kotlinx.coroutines.flow.Flow

class ReadTimerListUseCase(private val timerRepository: TimerRepository){
    suspend fun execute(): Flow<MutableList<Timer>> {
        return timerRepository.getTimerList()
    }
}