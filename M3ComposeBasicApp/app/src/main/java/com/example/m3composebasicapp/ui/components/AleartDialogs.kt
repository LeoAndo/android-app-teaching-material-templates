package com.example.m3composebasicapp.ui.components

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.m3composebasicapp.ui.theme.M3ComposeBasicAppTheme

@Composable
fun AppAlertDialog(
    titleText: String? = null,
    messageText: String? = null,
    confirmText: String? = null,
    dismissText: String? = null,
    openDialog: Boolean,
    onDismissRequest: () -> Unit,
    onClickConfirmButton: () -> Unit,
    onClickDismissButton: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                onDismissRequest()
            },
            title = {
                titleText?.let { Text(text = it) }
            },
            text = {
                messageText?.let { Text(text = it) }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onClickConfirmButton()
                    }
                ) {
                    confirmText?.let { Text(it) }
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onClickDismissButton()
                    }
                ) {
                    dismissText?.let { Text(text = it) }
                }
            }
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
fun AppAlertDialog_Preview_Show() {
    M3ComposeBasicAppTheme {
        AppAlertDialog(
            openDialog = true,
            titleText = "Dialog Title",
            messageText = "Has TODO ITEM completed?",
            confirmText = "Yes",
            dismissText = "No",
            onDismissRequest = { },
            onClickConfirmButton = {},
            onClickDismissButton = { })
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
fun AppAlertDialog_Preview_Hide() {
    M3ComposeBasicAppTheme {
        AppAlertDialog(
            openDialog = false,
            titleText = "Dialog Title",
            messageText = "Has TODO ITEM completed?",
            confirmText = "Yes",
            dismissText = "No",
            onDismissRequest = { },
            onClickConfirmButton = {},
            onClickDismissButton = { })
    }
}