package com.maahiway.userssampleapp.ui.state

sealed class UiState {
    data object Loading : UiState()
    data class Success(val data: List<Any>) : UiState()
    data class Error(val message: String) : UiState()

}