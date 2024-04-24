package com.alexanderostanin.myfavoritetimer.presentation.screen.homescreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alexanderostanin.myfavoritetimer.domain.model.Timer
import com.alexanderostanin.myfavoritetimer.navigation.Routes
import com.alexanderostanin.myfavoritetimer.presentation.viewmodel.TimerViewModel
import com.alexanderostanin.myfavoritetimer.utils.longToTimeString

@Composable
fun TimerItemCard(
    navController: NavHostController,
    timerViewModel: TimerViewModel,
    timerItem: Timer
) {
    Card(
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                timerViewModel.buildCurrentTimer(timerItem)
                navController.navigate(route = Routes.TimerScreen.route)
            },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = timerItem.name,
                style = TextStyle(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = longToTimeString(timerItem.duration),
                style = TextStyle(
                    fontSize = 24.sp
                )
            )
        }
    }
}