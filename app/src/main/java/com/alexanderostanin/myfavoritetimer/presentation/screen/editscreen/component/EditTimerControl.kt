package com.alexanderostanin.myfavoritetimer.presentation.screen.editscreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alexanderostanin.myfavoritetimer.R
import com.alexanderostanin.myfavoritetimer.presentation.screen.component.TimerTitleText
import com.alexanderostanin.myfavoritetimer.presentation.screen.component.TimeLeftText
import com.alexanderostanin.myfavoritetimer.presentation.viewmodel.TimerViewModel
import com.alexanderostanin.myfavoritetimer.utils.Constants
import java.lang.StringBuilder

@Composable
fun EditTimerControl(
    navController: NavController,
    timerViewModel: TimerViewModel
) {
    val name by timerViewModel.timerName.observeAsState(initial = "")
    val hours by timerViewModel.hours.observeAsState(initial = 0)
    val minutes by timerViewModel.minutes.observeAsState(initial = 0)
    val seconds by timerViewModel.seconds.observeAsState(initial = 0)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimerTitleText(titleText = name)
        
        OutlinedTextField(
            value = name,
            onValueChange = {
                timerViewModel.onNameUpdate(it)
            },
            label = { Text(text = stringResource(id = R.string.enter_timer_name)) }
        )

        Spacer(modifier = Modifier.height(20.dp))

        TimeLeftText(valueText =
        StringBuilder()
            .append(if (hours <10) "0$hours" else hours)
            .append(":")
            .append(if (minutes <10) "0$minutes" else minutes)
            .append(":")
            .append(if (seconds <10) "0$seconds" else seconds)
            .toString()
        )
        
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            EditCounterButton(timerViewModel, Constants.HOURS_TYPE)
            EditCounterButton(timerViewModel, Constants.MINUTES_TYPE)
            EditCounterButton(timerViewModel, Constants.SECONDS_TYPE)
        }
    }

}