package com.alexanderostanin.myfavoritetimer.presentation.screen.homescreen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alexanderostanin.myfavoritetimer.R
import com.alexanderostanin.myfavoritetimer.domain.model.Timer
import com.alexanderostanin.myfavoritetimer.navigation.Routes
import com.alexanderostanin.myfavoritetimer.presentation.viewmodel.TimerViewModel


@Composable
fun HomeActionButton(
    navController: NavHostController,
    timerViewModel: TimerViewModel
)
{

    LargeFloatingActionButton(
        onClick = {
            timerViewModel.buildCurrentTimer(Timer(0, "", 0))
            navController.navigate(route = Routes.EditTimeScreen.route)
        },
        shape = CircleShape,
        modifier = Modifier
            .padding(20.dp)
    ) {
        Icon(
            imageVector =  Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.create_new_timer)
        )
    }
}