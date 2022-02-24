package com.example.m3composebasicapp.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
internal class HomeViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf<HomeUiState>(HomeUiState.Initial)
        private set

    init {
        viewModelScope.launch {
            uiState = HomeUiState.Loading
            val data = buildList {
                repeat(100) {
                    add("item: $it")
                }
            }
            delay(1500L)
            uiState = HomeUiState.Data(data)
        }
    }
}