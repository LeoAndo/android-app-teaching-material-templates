package com.example.m3composebasicapp.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    navigateToNextScreen: (String) -> Unit,
) {
    var queryText by remember { mutableStateOf("") }
    var isEnableBtn by remember { mutableStateOf(false) }
    SearchContent(
        modifier = modifier,
        queryText = queryText,
        isEnableBtn = isEnableBtn,
        navigateToNextScreen = navigateToNextScreen,
        onValueChange = {
            queryText = it
            isEnableBtn = it.isNotEmpty()
        },
    )
}

@Composable
internal fun SearchContent(
    modifier: Modifier = Modifier,
    queryText: String,
    isEnableBtn: Boolean,
    navigateToNextScreen: (String) -> Unit,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {

        OutlinedTextField(
            value = queryText,
            onValueChange = onValueChange,
            label = { Text(text = "query word") },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                navigateToNextScreen(queryText)
            }, modifier = Modifier
                .fillMaxWidth(),
            enabled = isEnableBtn
        ) {
            Text(text = "go_to_result_screen")
        }
    }
}