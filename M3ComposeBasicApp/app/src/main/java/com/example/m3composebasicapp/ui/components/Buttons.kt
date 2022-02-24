package com.example.m3composebasicapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavController

@Composable
fun NavigateBackButton(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    // Use LocalLifecycleOwner.current as a proxy for the NavBackStackEntry
    // associated with this Composable
    if (navController.currentBackStackEntry == LocalLifecycleOwner.current &&
        navController.previousBackStackEntry != null
    ) {
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(containerColor = Red.copy(alpha = 0.7f)),
            modifier = modifier
        ) {
            Text(text = "Go to Previous screen")
        }
    }
}

@Composable
fun NavigateBackIconButton(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    if (navController.currentBackStackEntry == LocalLifecycleOwner.current &&
        navController.previousBackStackEntry != null
    ) {
        IconButton(
            onClick = { navController.popBackStack() }
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "go to back previous page"
            )
        }
    }
}