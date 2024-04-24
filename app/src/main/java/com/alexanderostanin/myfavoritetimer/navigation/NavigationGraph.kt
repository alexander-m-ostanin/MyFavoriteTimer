package com.alexanderostanin.myfavoritetimer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexanderostanin.myfavoritetimer.presentation.screen.editscreen.EditTimerScreen
import com.alexanderostanin.myfavoritetimer.presentation.screen.homescreen.HomeScreen
import com.alexanderostanin.myfavoritetimer.presentation.screen.timerscreen.TimerScreen
import com.alexanderostanin.myfavoritetimer.presentation.viewmodel.TimerViewModel

@Composable
fun NavigationGraph(
    timerViewModel: TimerViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen.route
    ) {
        composable(route = Routes.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                timerViewModel = timerViewModel
            )
        }
        composable(route = Routes.TimerScreen.route){
            TimerScreen(
                navController = navController,
                timerViewModel = timerViewModel
            )
        }
        composable(route = Routes.EditTimeScreen.route){
            EditTimerScreen(
                navController = navController,
                timerViewModel = timerViewModel
            )
        }
    }
}