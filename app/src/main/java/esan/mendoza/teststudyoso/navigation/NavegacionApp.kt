package esan.mendoza.teststudyoso.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import esan.mendoza.teststudyoso.presentation.home.Home
import esan.mendoza.teststudyoso.presentation.auth.LoginScreen
import esan.mendoza.teststudyoso.presentation.auth.RegisterScreen
import esan.mendoza.teststudyoso.presentation.pages.StudyOsoLandingScreen

@Composable
fun NavegacionApp(
    isDarkTheme: Boolean = false
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "landing") {
        composable("landing") { StudyOsoLandingScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { Home(navController) }
        composable("login") { LoginScreen(navController) }

    }
}



