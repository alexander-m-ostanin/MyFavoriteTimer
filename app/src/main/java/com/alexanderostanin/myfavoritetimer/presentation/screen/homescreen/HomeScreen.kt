package com.alexanderostanin.myfavoritetimer.presentation.screen.homescreen

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
import com.alexanderostanin.myfavoritetimer.presentation.screen.homescreen.component.HomeActionButton
import com.alexanderostanin.myfavoritetimer.presentation.screen.homescreen.component.TimerListGrid
import com.alexanderostanin.myfavoritetimer.presentation.screen.homescreen.component.HomeTopAppBar
import com.alexanderostanin.myfavoritetimer.presentation.viewmodel.TimerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    timerViewModel: TimerViewModel
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            HomeActionButton(
                navController = navController,
                timerViewModel = timerViewModel
            )
        },
        topBar = {
            HomeTopAppBar(
                navController = navController
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            TimerListGrid(
                navController = navController,
                timerViewModel = timerViewModel
            )
        }
    }
}





