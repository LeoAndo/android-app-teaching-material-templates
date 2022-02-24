package com.example.m3composebasicapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.m3composebasicapp.ui.theme.M3ComposeBasicAppTheme

@Composable
fun ErrorMessage(
    modifier: Modifier = Modifier,
    message: String,
    onClickReload: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = message)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onClickReload) {
            Text("reload")
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PIXEL_4
)
@Composable
fun ErrorMessagePreview() {
    M3ComposeBasicAppTheme {
        ErrorMessage(
            message = "Could not load.",
            onClickReload = {},
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}