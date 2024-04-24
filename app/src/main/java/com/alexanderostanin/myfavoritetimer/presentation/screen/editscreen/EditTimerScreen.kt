package com.alexanderostanin.myfavoritetimer.presentation.screen.editscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import com.alexanderostanin.myfavoritetimer.presentation.screen.editscreen.component.EditActionButton
import com.alexanderostanin.myfavoritetimer.presentation.screen.editscreen.component.EditTimerControl
import com.alexanderostanin.myfavoritetimer.presentation.screen.editscreen.component.EditTopAppBar
import com.alexanderostanin.myfavoritetimer.presentation.viewmodel.TimerViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTimerScreen(
    navController: NavController,
    timerViewModel: TimerViewModel
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            EditActionButton(
                navController = navController,
                timerViewModel = timerViewModel
            )
        },
        topBar = {
            EditTopAppBar(
                navController = navController,
                timerViewModel = timerViewModel
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            EditTimerControl(
                navController = navController,
                timerViewModel = timerViewModel
            )
        }
    }

}