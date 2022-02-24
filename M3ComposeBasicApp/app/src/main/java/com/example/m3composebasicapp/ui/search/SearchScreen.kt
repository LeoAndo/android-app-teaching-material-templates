package com.example.m3composebasicapp.ui.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.m3composebasicapp.ui.extentions.mainContentPadding
import com.example.m3composebasicapp.ui.theme.M3ComposeBasicAppTheme

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
                .fillMaxWidth()
                .testTag("test_tag_button"),
            enabled = isEnableBtn
        ) {
            Text(text = "go_to_result_screen")
        }
    }
}

@Preview(
    name = "default",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PIXEL_4,
    showSystemUi = true
)
@Preview(
    name = "dark mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_4,
    showSystemUi = true
)
@Composable
fun SearchContentPreview() {
    M3ComposeBasicAppTheme {
        Surface {
            SearchContent(
                modifier = Modifier.mainContentPadding(PaddingValues(12.dp, 12.dp, 12.dp, 92.dp)),
                "queryText",
                true,
                navigateToNextScreen = {},
                onValueChange = {},
            )
        }
    }
}