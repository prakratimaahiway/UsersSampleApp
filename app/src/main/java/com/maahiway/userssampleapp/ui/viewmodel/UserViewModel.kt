package com.maahiway.userssampleapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maahiway.userssampleapp.data.repository.UserRepository
import com.maahiway.userssampleapp.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _users = MutableStateFlow<UiState>(UiState.Loading)
    val users: StateFlow<UiState> get() = _users

    init {
            getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            _users.value = UiState.Loading
            try {
                val userList = userRepository.getUser()
                _users.value = UiState.Success(userList)
            } catch (e: Exception) {
                _users.value = UiState.Error(e.message ?: "Unknown error")
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