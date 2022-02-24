package com.example.m3composebasicapp.ui.home

internal sealed interface HomeUiState {
    object Initial : HomeUiState
    object NoPhotos : HomeUiState
    data class Photos(val results: List<String> = emptyList()) : HomeUiState
    object Loading : HomeUiState
    data class Error(val error: Throwable) : HomeUiState
}