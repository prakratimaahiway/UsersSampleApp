package com.maahiway.userssampleapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.maahiway.userssampleapp.data.model.User
import com.maahiway.userssampleapp.ui.state.UiState
import com.maahiway.userssampleapp.ui.viewmodel.UserViewModel

@Composable
fun UserDetailsScreen(
    navController: NavController,
    userId: Int,
    userViewModel: UserViewModel = hiltViewModel()
) {

    val userState = remember { mutableStateOf<UiState>(UiState.Loading) }

    LaunchedEffect(userId) {
        userViewModel.getUserById(userId) { result ->
            userState.value = result
        }
    }

    when (val state = userState.value) {
        is UiState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        }
        is UiState.Success ->
        {
            val user = (state.data as? List<User>)?.firstOrNull()
            if (user != null) {
                UserDetail(user)
            } else {
                Text("No user found")
            }
        }
        is UiState.Error -> {
            Text(text = "Error: ${state.message}", modifier = Modifier.fillMaxSize())
        }
    }

}
@Composable
fun UserDetail(user: User) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Name: ${user.name}", style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Username: ${user.username}")
        Text(text = "Email: ${user.email}")
        Text(text = "Phone: ${user.phone}")
        Text(text = "Website: ${user.website}")
        Text(text = "Company: ${user.company.name}")
        Text(text = "Address: ${user.address.city}")
    }
}