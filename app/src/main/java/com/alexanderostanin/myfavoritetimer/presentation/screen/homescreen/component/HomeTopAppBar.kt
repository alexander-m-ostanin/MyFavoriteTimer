package com.alexanderostanin.myfavoritetimer.presentation.screen.homescreen.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import com.alexanderostanin.myfavoritetimer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    navController: NavHostController
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
//        navigationIcon = {
//            IconButton(onClick = { /* do something */ }) {
//                Icon(
//                    imageVector = Icons.Filled.ArrowBack,
//                    contentDescription = "Localized description"
//                )
//            }
//        },
//        actions = {
//            IconButton(onClick = { /* do something */ }) {
//                Icon(
//                    imageVector = Icons.Filled.Menu,
//                    contentDescription = "Localized description"
//                )
//            }
//        }
    )
}