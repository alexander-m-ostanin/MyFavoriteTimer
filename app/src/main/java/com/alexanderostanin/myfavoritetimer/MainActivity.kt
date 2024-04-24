package com.alexanderostanin.myfavoritetimer

import android.Manifest
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.alexanderostanin.myfavoritetimer.navigation.NavigationGraph
import com.alexanderostanin.myfavoritetimer.presentation.viewmodel.TimerViewModel
import com.alexanderostanin.myfavoritetimer.presentation.viewmodel.TimerServiceReceiver
import com.alexanderostanin.myfavoritetimer.ui.theme.MyFavoriteTimerTheme
import com.alexanderostanin.myfavoritetimer.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    //инициализация через Koin DI
    private val timerViewModel by viewModel<TimerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )

            IntentFilter(Constants.SERVICE_BROADCAST_INTENT).also {
                registerReceiver(
                    TimerServiceReceiver(timerViewModel),
                    it,
                    RECEIVER_NOT_EXPORTED
                )
            }
        }



        setContent {

            MyFavoriteTimerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph(
                        timerViewModel = timerViewModel
                    )
                }
            }
        }
    }
}
