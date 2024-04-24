package com.alexanderostanin.myfavoritetimer.di

import com.alexanderostanin.myfavoritetimer.presentation.viewmodel.TimerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
      viewModel<TimerViewModel>{
        TimerViewModel(
            application = get(),
            readTimerListUseCase = get(),
            createTimerUseCase = get(),
            updateTimerUseCase = get(),
            deleteTimerUseCase = get(),
            startTimerUseCase = get(),
            stopTimerUseCase = get(),
            resetTimerUseCase = get()
        )
    }
}
