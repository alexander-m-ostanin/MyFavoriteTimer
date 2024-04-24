package com.alexanderostanin.myfavoritetimer.presentation.screen.timerscreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alexanderostanin.myfavoritetimer.R
import com.alexanderostanin.myfavoritetimer.presentation.screen.component.TimerTitleText
import com.alexanderostanin.myfavoritetimer.presentation.screen.component.TimeLeftText
import com.alexanderostanin.myfavoritetimer.domain.model.TimerState
import com.alexanderostanin.myfavoritetimer.presentation.viewmodel.TimerViewModel

@Composable
fun TimerControl(
    navController: NavHostController,
    timerViewModel: TimerViewModel
){

    val timerState by timerViewModel.timerState.observeAsState(initial = TimerState.IS_PAUSED)
    val progress by timerViewModel.progress.observeAsState(initial = 0f)
    val name by timerViewModel.timerName.observeAsState(initial = "")
    val timeLeftText by timerViewModel.timeLeftText.observeAsState(initial = "")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TimerTitleText(titleText = name)

        Spacer(modifier = Modifier.height(20.dp))

        TimeLeftText(valueText = timeLeftText)

        Box(
            modifier = Modifier
                .padding(50.dp),
            contentAlignment = Alignment.Center,
        ){

            IconButton(
                onClick = {
                    when (timerState){
                        TimerState.IS_PLAYING -> timerViewModel.stopTimer()
                        TimerState.IS_PAUSED -> timerViewModel.startTimer()
                        TimerState.IS_RINGING -> timerViewModel.resetTimer()
                    }
                },
                modifier = Modifier
                    .size(150.dp)
            ) {
                Icon(
                    painter =
                    when (timerState){
                        TimerState.IS_RINGING -> painterResource(R.drawable.alarm)
                        TimerState.IS_PLAYING -> painterResource(R.drawable.pause)
                        TimerState.IS_PAUSED -> painterResource(R.drawable.play_arrow)
                    },
                    contentDescription = when (timerState){
                        TimerState.IS_PLAYING -> "Stop CountDown"
                        TimerState.IS_PAUSED -> "Start CountDown"
                        TimerState.IS_RINGING -> "Reset CountDown"
                    },
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .alpha(0.5f)
                        .fillMaxSize()
                )
            }

            CircularProgressIndicator(
                progress = progress,
                modifier = Modifier.size(160.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }

        Button(onClick = {
            timerViewModel.resetTimer()
        }) {
            Text(text = "Reset Timer")
        }
    }
}
