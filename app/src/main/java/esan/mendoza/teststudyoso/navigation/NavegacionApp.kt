package com.menfroyt.studyoso.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import esan.mendoza.teststudyoso.presentation.ui.home.Home
import esan.mendoza.teststudyoso.presentation.ui.pages.StudyOsoLandingScreen
import esan.mendoza.teststudyoso.di.AppViewModelProvider
import esan.mendoza.teststudyoso.presentation.ui.auth.LoginScreen
import esan.mendoza.teststudyoso.presentation.ui.auth.RegisterScreen
import esan.mendoza.teststudyoso.presentation.viewModel.UsuarioViewModel

@Composable
fun NavegacionApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "landing") {
        composable("landing") { StudyOsoLandingScreen(navController) }
        composable("register") {
            val usuarioViewModel: UsuarioViewModel =
                viewModel(factory = AppViewModelProvider.UsuarioViewModelFactory)
            RegisterScreen(navController = navController, usuarioViewModel = usuarioViewModel)
        }
        composable("home") { Home(navController) }
        composable("login") {
            val usuarioViewModel: UsuarioViewModel =
                viewModel(factory = AppViewModelProvider.UsuarioViewModelFactory)
            LoginScreen(navController = navController, usuarioViewModel = usuarioViewModel)
        }

    }
}



