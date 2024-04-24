package com.alexanderostanin.myfavoritetimer.presentation.screen.editscreen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alexanderostanin.myfavoritetimer.R
import com.alexanderostanin.myfavoritetimer.navigation.Routes
import com.alexanderostanin.myfavoritetimer.presentation.viewmodel.TimerViewModel

@Composable
fun EditActionButton(
    navController: NavController,
    timerViewModel: TimerViewModel
){
    LargeFloatingActionButton(
        onClick = {
            timerViewModel.onSaveChanges()
            navController.navigate(Routes.TimerScreen.route)
        },
        shape = CircleShape,
        modifier = Modifier
            .padding(20.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Done,
            contentDescription = stringResource(id = R.string.save_timer)
        )
    }
}