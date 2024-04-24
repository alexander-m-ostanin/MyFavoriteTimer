package com.alexanderostanin.myfavoritetimer

import android.app.Application
import com.alexanderostanin.myfavoritetimer.di.appModule
import com.alexanderostanin.myfavoritetimer.di.dataModule
import com.alexanderostanin.myfavoritetimer.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application()  {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule))
        }

//        IntentFilter(Constants.SERVICE_BROADCAST_INTENT).also {
//            registerReceiver(TimerViewModel.TimerServiceReceiver(), it, RECEIVER_NOT_EXPORTED)
//        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
//            val notificationChannel = NotificationChannel(
//                "timer_notification_channel",
//                "Timer Notofocation",
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            val notificationManager =
//                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(notificationChannel)
//        }
    }
}