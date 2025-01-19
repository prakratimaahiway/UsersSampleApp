package com.maahiway.userssampleapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.maahiway.userssampleapp.R
import com.maahiway.userssampleapp.data.model.User
import com.maahiway.userssampleapp.ui.state.UiState
import com.maahiway.userssampleapp.ui.viewmodel.UserViewModel

@Composable
fun UserListScreen(navController: NavController, viewModel: UserViewModel = hiltViewModel()) {

    when (val state = viewModel.users.value) {
        is UiState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        }

        is UiState.Success -> {
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(state.data as List<User>) { user ->
                    UserItem(user = user, onClick = {
                        navController.navigate("userDetail/${user.id}")
                    })
                }
            }
        }
        is UiState.Error -> {
            Text(text = "Error: ${state.message}", modifier = Modifier.fillMaxSize())
        }
    }

}

@Composable
fun UserItem(user: User, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), // Use an icon for the user
            contentDescription = "User Icon",
            modifier = Modifier
                .size(40.dp)
                .padding(end = 16.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(text = user.name, style = MaterialTheme.typography.labelMedium)
            Text(text = user.username, style = MaterialTheme.typography.labelLarge)
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun PreviewUserListScreen() {
    val navController = rememberNavController()
    UserListScreen(navController = navController)
}*/
