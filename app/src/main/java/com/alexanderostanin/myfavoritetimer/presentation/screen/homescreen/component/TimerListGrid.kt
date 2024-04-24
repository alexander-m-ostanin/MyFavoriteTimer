package com.alexanderostanin.myfavoritetimer.presentation.screen.homescreen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alexanderostanin.myfavoritetimer.presentation.viewmodel.TimerViewModel

@Composable
fun TimerListGrid(
    navController: NavHostController,
    timerViewModel: TimerViewModel
){
    timerViewModel.getAllTimers()
    val timerList by timerViewModel.timerList.observeAsState()

    Column (
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        timerList?.let {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                contentPadding = PaddingValues(4.dp),
            ) {
                itemsIndexed(timerList!!) { _, timer ->
                    TimerItemCard(
                        navController = navController,
                        timerViewModel = timerViewModel,
                        timerItem = timer)
                }
            }
        }?: Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            text = "No items yet. Create your first timer."
        )


    }
}