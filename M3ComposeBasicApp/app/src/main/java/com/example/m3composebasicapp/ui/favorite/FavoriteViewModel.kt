package com.example.m3composebasicapp.ui.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class FavoriteViewModel @Inject constructor() : ViewModel() {
    var uiState by mutableStateOf<FavoriteUiState>(FavoriteUiState.Initial)

    init {
        val data = buildList {
            repeat(10) {
                add("item: $it")
            }
        }
        uiState = FavoriteUiState.UpdateTodoList(data)
    }
}