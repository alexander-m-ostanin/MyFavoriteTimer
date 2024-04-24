package com.alexanderostanin.myfavoritetimer.presentation.screen.timerscreen.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import com.alexanderostanin.myfavoritetimer.R
import com.alexanderostanin.myfavoritetimer.navigation.Routes
import com.alexanderostanin.myfavoritetimer.presentation.viewmodel.TimerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerTopAppBar(
    navController: NavHostController,
    timerViewModel: TimerViewModel
) {

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                timerViewModel.resetTimer()
                timerViewModel.stopService()
                navController.navigate(route = Routes.HomeScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.go_back_home)
                )
            }
        },
        actions = {
            IconButton(onClick = {
                timerViewModel.resetTimer()
                navController.navigate(Routes.EditTimeScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = stringResource(id = R.string.go_edit_timer)
                )
            }
            IconButton(onClick = {
                timerViewModel.resetTimer()
                timerViewModel.stopService()
                timerViewModel.currentTimer.value?.let { timerViewModel.deleteTimer(it) }
                navController.navigate(Routes.HomeScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = stringResource(id = R.string.go_delete_timer)
                )
            }
        }
    )
}