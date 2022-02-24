package com.example.m3composebasicapp.ui.favorite

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.TextField
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.m3composebasicapp.R
import com.example.m3composebasicapp.ui.components.AppAlertDialog
import com.example.m3composebasicapp.ui.theme.M3ComposeBasicAppTheme

// Stateful Composable that depends on ViewModel.
@Composable
internal fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel(),
) {
    var titleText by remember { mutableStateOf("") }
    var memoText by remember { mutableStateOf("") }
    var enabledAddButton by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        AddTodoItemContent(
            titleText = titleText,
            memoText = memoText,
            enabledAddButton = enabledAddButton,
            onValueChangeTitleText = {
                titleText = it
                enabledAddButton = memoText.isNotBlank() && titleText.isNotBlank()
            },
            onValueChangeMemoText = {
                memoText = it
                enabledAddButton = memoText.isNotBlank() && titleText.isNotBlank()
            },
            onClickDeleteAllTodoItemsButton = {}
        )
        Spacer(modifier = Modifier.height(12.dp))
        TodoListContent(uiState = viewModel.uiState)
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
internal fun AddTodoItemContent(
    titleText: String,
    memoText: String,
    enabledAddButton: Boolean,
    onValueChangeTitleText: (String) -> Unit,
    onValueChangeMemoText: (String) -> Unit,
    onClickDeleteAllTodoItemsButton: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(value = titleText, onValueChange = onValueChangeTitleText, label = {
            Text(text = "Input Here Todo Title.")
        })
        TextField(value = memoText, onValueChange = onValueChangeMemoText, label = {
            Text(text = "Input Here Todo Memo.")
        })
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ElevatedButton(onClick = {
            }, enabled = enabledAddButton) {
                Text(text = stringResource(id = R.string.add_todo_item))
            }

            FilledTonalButton(
                onClick = {
                    onClickDeleteAllTodoItemsButton()
                }
            ) {
                Text(text = stringResource(id = R.string.delete_todo_items))
            }
        }
    }
}

@Composable
internal fun TodoListContent(uiState: FavoriteUiState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        when (uiState) {
            FavoriteUiState.Initial -> {

            }
            is FavoriteUiState.Error -> {
                Text(text = uiState.errorMessage)
            }
            is FavoriteUiState.UpdateTodoList -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    itemsIndexed(uiState.todoList) { _, todoData ->
                        Text(
                            text = todoData,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
fun AddTodoItemContent_Preview_Button_Disable() {
    M3ComposeBasicAppTheme {
        AddTodoItemContent(
            titleText = "",
            memoText = "",
            enabledAddButton = false,
            onValueChangeTitleText = {},
            onValueChangeMemoText = {},
            onClickDeleteAllTodoItemsButton = {}
        )
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
fun AddTodoItemContent_Preview_Button_Enable() {
    M3ComposeBasicAppTheme {
        AddTodoItemContent(
            titleText = "",
            memoText = "",
            enabledAddButton = true,
            onValueChangeTitleText = {},
            onValueChangeMemoText = {},
            onClickDeleteAllTodoItemsButton = {}
        )
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
fun TodoListContent_Preview_Error() {
    M3ComposeBasicAppTheme {
        TodoListContent(
            uiState = FavoriteUiState.Error(errorMessage = "error!"),
        )
    }
}