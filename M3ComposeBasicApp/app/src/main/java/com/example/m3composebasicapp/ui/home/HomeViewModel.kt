package com.example.m3composebasicapp.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@HiltViewModel
internal class HomeViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf<HomeUiState>(HomeUiState.Initial)
        private set

    init {
        val data = buildList {
            repeat(10) {
                add("item: $it")
            }
        }
        uiState = HomeUiState.Photos(data)
    }
}