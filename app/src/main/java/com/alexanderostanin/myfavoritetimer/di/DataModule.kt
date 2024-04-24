package com.alexanderostanin.myfavoritetimer.di

import android.content.Context
import androidx.room.Room
import com.alexanderostanin.myfavoritetimer.data.db.TimerDatabase
import com.alexanderostanin.myfavoritetimer.data.repository.TimerRepositoryImpl
import com.alexanderostanin.myfavoritetimer.domain.repository.TimerRepository
import com.alexanderostanin.myfavoritetimer.utils.Constants.TIMER_DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<TimerRepository> {
        TimerRepositoryImpl(dao = get())
    }
    single { provideDatabase(context = androidContext()) }
    single { provideDao(db = get()) }

}

fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, TimerDatabase::class.java, TIMER_DATABASE_NAME)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

fun provideDao(db: TimerDatabase) = db.getTimerDao()
