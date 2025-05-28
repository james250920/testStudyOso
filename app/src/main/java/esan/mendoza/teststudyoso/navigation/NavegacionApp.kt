package esan.mendoza.teststudyoso.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import esan.mendoza.teststudyoso.presentation.home.Home
import esan.mendoza.teststudyoso.presentation.auth.LoginScreen
import esan.mendoza.teststudyoso.presentation.auth.RegisterScreen
import esan.mendoza.teststudyoso.presentation.pages.StudyOsoLandingScreen

@Composable
fun NavegacionApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "landing") {
        composable("landing") { StudyOsoLandingScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable(
            route = "home/{usuarioId}",
            arguments = listOf(navArgument("usuarioId") { type = NavType.IntType })
        ) { backStackEntry ->
            val usuarioId = backStackEntry.arguments?.getInt("usuarioId") ?: 0
            Home(
                navController = navController,
                usuarioId = usuarioId
            )
        }
        composable("login") { LoginScreen(navController) }

    }
}



