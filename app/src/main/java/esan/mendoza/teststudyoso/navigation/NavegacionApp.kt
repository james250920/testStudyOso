package esan.mendoza.teststudyoso.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import esan.mendoza.teststudyoso.presentation.ui.*
import esan.mendoza.teststudyoso.presentation.viewModel.UsuarioViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import esan.mendoza.teststudyoso.presentation.viewModel.CursoViewModel
import esan.mendoza.teststudyoso.presentation.viewModel.TareaViewModel
import esan.mendoza.teststudyoso.presentation.viewModel.CalificacionViewModel
import esan.mendoza.teststudyoso.presentation.viewModel.HorarioViewModel
import esan.mendoza.teststudyoso.presentation.viewModel.TipoPruebaViewModel

@Composable
fun NavegacionApp(

) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "landing") {
        composable("landing") {StudyOsoLandingScreen(navController)}
        composable("register") { RegisterScreen(navController) }
        composable("home") { Home(navController)}
        composable("login") {LoginScreen(navController )}
        composable("perfil") {PerfilScreen()}
        composable("configuracion") { ConfiguracionScreen()}
        composable("pomodoro") {PomodoroScreen()}
        composable("AgregarCursos"){}
        composable("Calificaciones") {}
        composable("ListaTareas") {}
        composable("MatrizEisenhower"){}
        composable("Calendario") {}
    }
}



