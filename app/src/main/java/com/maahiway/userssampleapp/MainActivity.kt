package com.maahiway.userssampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maahiway.userssampleapp.ui.screen.SplashScreen
import com.maahiway.userssampleapp.ui.screen.UserDetailsScreen
import com.maahiway.userssampleapp.ui.screen.UserListScreen
import com.maahiway.userssampleapp.ui.theme.UsersSampleAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UsersSampleAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "splash") {
                        composable("splash") {
                            SplashScreen(navController = navController)
                        }
                        composable("userList") {
                            UserListScreen(navController = navController)
                        }
                        composable("userDetail/{userId}") { navBackStackEntry ->
                            val userId =
                                navBackStackEntry.arguments?.getString("userId")?.toInt() ?: 0
                            UserDetailsScreen(navController = navController, userId = userId)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UsersSampleAppTheme {
        Greeting("Android")
    }
}