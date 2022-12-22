package com.hemerson.msn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hemerson.msn.routes.Routes
import com.hemerson.msn.ui.screens.LoginScreen
import com.hemerson.msn.ui.screens.RegisterScreen
import com.hemerson.msn.ui.theme.MSNTheme
import com.hemerson.msn.ui.viewmodels.LoginScreenViewModel
import com.hemerson.msn.ui.viewmodels.RegisterScreenViewModel
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MSNTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BindLoginFlow()
                }
            }
        }
    }
}

@Composable
fun BindLoginFlow() {
    Column(modifier = Modifier.fillMaxSize()) {
        SetupNavHost()
    }
}

@Composable
private fun SetupNavHost() {

    val navController = rememberNavController()
    val loginScreenViewModel: LoginScreenViewModel = getViewModel()
    val registerScreenViewModel: RegisterScreenViewModel = getViewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.Login.routeName
        ) {
        composable(route = Routes.Login.routeName) {
            LoginScreen(viewModel = loginScreenViewModel, navController = navController)
        }
        composable(route = Routes.Register.routeName) {
            RegisterScreen(viewModel = registerScreenViewModel)
        }
    }
}


