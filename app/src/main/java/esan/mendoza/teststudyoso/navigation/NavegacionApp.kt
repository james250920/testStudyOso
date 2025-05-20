package esan.mendoza.teststudyoso.navigation

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
    usuarioViewModel: UsuarioViewModel,
    cursoViewModel: CursoViewModel,
    tareaViewModel: TareaViewModel,
    calificacionViewModel: CalificacionViewModel,
    horarioViewModel: HorarioViewModel,

) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("landing") {
            StudyOsoLandingScreen(navController)
        }
        composable("register") {
            RegisterScreen(
                usuarioViewModel,
                navController,

            )
        }
        composable("home") {
            Home(
            )
        }

    }
}



