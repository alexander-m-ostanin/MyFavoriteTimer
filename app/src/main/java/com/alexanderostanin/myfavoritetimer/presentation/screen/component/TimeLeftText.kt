package com.alexanderostanin.myfavoritetimer.presentation.screen.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun TimeLeftText(
    valueText: String
){
    Text(text = valueText, fontSize = 48.sp)
}