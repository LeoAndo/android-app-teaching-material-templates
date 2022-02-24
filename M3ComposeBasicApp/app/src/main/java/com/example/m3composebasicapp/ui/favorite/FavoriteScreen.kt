package com.example.m3composebasicapp.ui.favorite

import androidx.compose.foundation.layout.*
import androidx.compose.material.TextField
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.m3composebasicapp.R
import com.example.m3composebasicapp.ui.components.AppAlertDialog

// Stateful Composable that depends on ViewModel.
@Composable
internal fun FavoriteScreen(modifier: Modifier = Modifier) {
    var titleText by remember { mutableStateOf("") }
    var enabledAddButton by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        FavoriteContent(
            titleText = titleText,
            enabledOpenDialogButton = enabledAddButton,
            onValueChangeTitleText = {
                titleText = it
                enabledAddButton = titleText.isNotBlank()
            },
            onClickButton = { openDialog = true }
        )
        Spacer(modifier = Modifier.height(12.dp))
        AppAlertDialog(
            openDialog = openDialog,
            titleText = "Dialog Title",
            messageText = "Has TODO ITEM completed?",
            confirmText = "Yes",
            dismissText = "No",
            onDismissRequest = { openDialog = false },
            onClickConfirmButton = { openDialog = false },
            onClickDismissButton = { openDialog = false })
    }
}

// stateless Composable.
@Composable
internal fun FavoriteContent(
    titleText: String,
    enabledOpenDialogButton: Boolean,
    onValueChangeTitleText: (String) -> Unit,
    onClickButton: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(value = titleText, onValueChange = onValueChangeTitleText, label = {
            Text(text = "Input Here Text.")
        })
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ElevatedButton(onClick = { onClickButton() }, enabled = enabledOpenDialogButton) {
                Text(text = stringResource(id = R.string.open_dialog))
            }
        }
    }
}