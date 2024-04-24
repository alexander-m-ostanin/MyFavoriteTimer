package com.alexanderostanin.myfavoritetimer.presentation.screen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun  TimerTitleText(
    titleText: String
)
{
    Text(
        modifier = Modifier
            .padding(24.dp),
        text = titleText,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        lineHeight = 32.sp
    )
}