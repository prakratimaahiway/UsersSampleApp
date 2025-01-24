package com.maahiway.userssampleapp.ui.screen

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SplashScreen(navController: NavController) {
    Handler(Looper.getMainLooper()).postDelayed({
        navController.navigate("userList") {
            popUpTo("splash") { inclusive = true }
        }
    }, 3000)

    // Splash UI
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
        Text(text = "Loading...", fontSize = 24.sp, modifier = Modifier.align(Alignment.Center))
    }
}