package com.alexanderostanin.myfavoritetimer.presentation.screen.editscreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alexanderostanin.myfavoritetimer.presentation.viewmodel.TimerViewModel
import com.alexanderostanin.myfavoritetimer.utils.Constants

@Composable
fun EditCounterButton(
    timerViewModel: TimerViewModel,
    counterType: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(80.dp)
            .height(200.dp)
    ) {

        ButtonContainer(
            timerViewModel,
            counterType,
            modifier = Modifier
        )

        CounterResultView(
            timerViewModel,
            counterType,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun ButtonContainer(
    timerViewModel: TimerViewModel,
    counterType: String,
    modifier: Modifier = Modifier,
) {
    timerViewModel.apply {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = Constants.CONTAINER_BACKGROUND_ALPHA_INITIAL))
                .padding(vertical = 8.dp)
        ) {
            // кнопка увеличения
            IconControlButton(
                icon = Icons.Outlined.KeyboardArrowUp,
                contentDescription = "Increase count",
                timerViewModel = timerViewModel,
                counterType = counterType,
                actionType = Constants.INCREASE_ACTION_TYPE
            )

            // кнопка уменьшения
            IconControlButton(
                icon = Icons.Outlined.KeyboardArrowDown,
                contentDescription = "Decrease count",
                timerViewModel = timerViewModel,
                counterType = counterType,
                actionType = Constants.DECREASE_ACTION_TYPE
            )
        }
    }
}

@Composable
private fun IconControlButton(
    icon: ImageVector,
    contentDescription: String,
    timerViewModel: TimerViewModel,
    counterType: String,
    actionType: String,
    modifier: Modifier = Modifier
) {
    timerViewModel.apply {
        IconButton(
            onClick = {
                if (actionType == Constants.INCREASE_ACTION_TYPE) onValueIncreaseClick(counterType)
                else onValueDecreaseClick(counterType)
            },
            modifier = modifier
                .size(48.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
private fun CounterResultView(
    timerViewModel: TimerViewModel,
    counterType: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(64.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        timerViewModel.apply {
            Text(
                text = getCountValue(valueType = counterType),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
            )
        }
    }
}