package com.example.m3composebasicapp.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.m3composebasicapp.ui.components.ErrorMessage
import com.example.m3composebasicapp.ui.components.FullScreenLoading
import com.example.m3composebasicapp.ui.extentions.mainContentPadding
import com.example.m3composebasicapp.ui.theme.M3ComposeBasicAppTheme

// Stateful Composable that depends on ViewModel.
@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    HomeContent(
        uiState = viewModel.uiState,
        modifier = modifier
    )
}

// stateless Composable.
@Composable
internal fun HomeContent(
    uiState: HomeUiState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
    ) {
        when (uiState) {
            is HomeUiState.Error -> {
                ErrorMessage(
                    message = "fetch error: ${uiState.error.message}",
                    onClickReload = { },
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize()
                )
            }
            HomeUiState.Initial, HomeUiState.Loading -> {
                FullScreenLoading()
            }
            HomeUiState.NoData -> {
                ErrorMessage(
                    message = "empty list.",
                    onClickReload = {},
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize()
                )
            }
            is HomeUiState.Data -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    itemsIndexed(uiState.results) { _, data ->
                        Text(
                            text = data,
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PIXEL_4,
    showSystemUi = true
)
@Composable
fun HomeContent_Preview_Loading() {
    M3ComposeBasicAppTheme {
        HomeContent(
            uiState = HomeUiState.Loading,
            modifier = Modifier.mainContentPadding(PaddingValues(12.dp, 12.dp, 12.dp, 92.dp))
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PIXEL_4,
    showSystemUi = true
)
@Composable
fun HomeContent_Preview_Error() {
    M3ComposeBasicAppTheme {
        HomeContent(
            uiState = HomeUiState.Error(error = Exception("test error")),
            modifier = Modifier.mainContentPadding(PaddingValues(12.dp, 12.dp, 12.dp, 92.dp))
        )
    }
}