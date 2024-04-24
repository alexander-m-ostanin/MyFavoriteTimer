package com.alexanderostanin.myfavoritetimer.presentation.screen.timerscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import com.alexanderostanin.myfavoritetimer.presentation.screen.timerscreen.component.TimerControl
import com.alexanderostanin.myfavoritetimer.presentation.screen.timerscreen.component.TimerTopAppBar
import com.alexanderostanin.myfavoritetimer.presentation.viewmodel.TimerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun TimerScreen(
    navController: NavHostController,
    timerViewModel: TimerViewModel
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
//        floatingActionButton = {
//            ActionButton(
//                navController = navController
//            )
//        },
        topBar = {
            TimerTopAppBar(
                navController = navController,
                timerViewModel = timerViewModel
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            TimerControl(
                navController = navController,
                timerViewModel = timerViewModel
            )
        }
    }
}



