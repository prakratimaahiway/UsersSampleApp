package com.maahiway.userssampleapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maahiway.userssampleapp.data.repository.UserRepository
import com.maahiway.userssampleapp.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _users = mutableStateOf<UiState>(UiState.Loading)
    val users: State<UiState> = _users

    init {
            getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            try {
                _users.value = UiState.Loading
                val userList = userRepository.getUser()
                _users.value = UiState.Success(userList)
            } catch (e: Exception) {
                _users.value = UiState.Error(e.message ?: "unknown error")
            }
        }
    }

    fun getUserById(userId: Int, onResult: (UiState) -> Unit) {
        viewModelScope.launch {
            try {
                onResult(UiState.Loading)
                val user = userRepository.getUserById(userId)
                onResult(UiState.Success(listOf(user))) // Wrap user in a list
            } catch (e: Exception) {
                onResult(UiState.Error(e.message ?: "Unknown error"))
            }
        }
    }
}