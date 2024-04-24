package com.alexanderostanin.myfavoritetimer.navigation

sealed class Routes (val route: String){
    data object HomeScreen: Routes(route = "home_screen")
    data object TimerScreen: Routes(route = "timer_screen")
    data object EditTimeScreen: Routes(route = "edit_time_screen")
}