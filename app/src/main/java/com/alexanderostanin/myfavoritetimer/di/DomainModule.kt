package com.alexanderostanin.myfavoritetimer.di

import com.alexanderostanin.myfavoritetimer.domain.usecase.CreateTimerUseCase
import com.alexanderostanin.myfavoritetimer.domain.usecase.DeleteTimerUseCase
import com.alexanderostanin.myfavoritetimer.domain.usecase.ReadTimerListUseCase
import com.alexanderostanin.myfavoritetimer.domain.usecase.ResetTimerUseCase
import com.alexanderostanin.myfavoritetimer.domain.usecase.StartTimerUseCase
import com.alexanderostanin.myfavoritetimer.domain.usecase.StopTimerUseCase
import com.alexanderostanin.myfavoritetimer.domain.usecase.UpdateTimerUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<ReadTimerListUseCase> {
        ReadTimerListUseCase(timerRepository = get())
    }
    factory<CreateTimerUseCase> {
        CreateTimerUseCase(timerRepository = get())
    }
    factory<UpdateTimerUseCase> {
        UpdateTimerUseCase(timerRepository = get())
    }
    factory<DeleteTimerUseCase> {
        DeleteTimerUseCase(timerRepository = get())
    }
    factory<StartTimerUseCase> {
        StartTimerUseCase(application = get())
    }
    factory<StopTimerUseCase> {
        StopTimerUseCase(application = get())
    }
    factory<ResetTimerUseCase> {
        ResetTimerUseCase(application = get())
    }
}