package com.example.m3composebasicapp.ui.favorite

internal sealed interface FavoriteUiState {
    object Initial : FavoriteUiState
    data class UpdateTodoList(val todoList: List<String>) : FavoriteUiState
    data class Error(val errorMessage: String) : FavoriteUiState
}