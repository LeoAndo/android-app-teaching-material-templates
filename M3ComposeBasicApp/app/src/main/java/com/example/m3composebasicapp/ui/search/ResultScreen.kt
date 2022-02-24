package com.example.m3composebasicapp.ui.search

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.m3composebasicapp.ui.components.NavigateBackButton
import com.example.m3composebasicapp.ui.components.NavigateBackIconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ResultScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    query: String,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) {
    // show SnackBar.
    var errorMessage: String? by remember { mutableStateOf(null) }
    LaunchedEffect(key1 = errorMessage, block = {
        Log.d("ResultContent", "LaunchedEffect: $errorMessage")
        errorMessage?.let { scaffoldState.snackbarHostState.showSnackbar(it) }
    })

    // TODO: refactor material3
    androidx.compose.material.Scaffold(
        scaffoldState = scaffoldState
    ) {
        ResultContent(
            navController = navController,
            modifier = modifier,
            showSnack = { errorMessage = query }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ResultContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    showSnack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "ResultScreen")
                },
                navigationIcon = {
                    NavigateBackIconButton(navController = navController)
                }
            )
        }
    ) {
        Column {
            Button(onClick = { showSnack() }) {
                Text(text = "show snackbar")
            }
            Spacer(modifier = Modifier.height(20.dp))
            NavigateBackButton(navController = navController, modifier = Modifier.fillMaxWidth())
        }
    }
}