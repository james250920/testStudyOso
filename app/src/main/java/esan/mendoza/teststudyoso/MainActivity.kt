package esan.mendoza.teststudyoso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import esan.mendoza.teststudyoso.navigation.NavegacionApp
import esan.mendoza.teststudyoso.presentation.viewModel.CalificacionViewModel
import esan.mendoza.teststudyoso.presentation.viewModel.CalificacionViewModelFactory
import esan.mendoza.teststudyoso.presentation.viewModel.CursoViewModel
import esan.mendoza.teststudyoso.presentation.viewModel.CursoViewModelFactory
import esan.mendoza.teststudyoso.presentation.viewModel.HorarioViewModel
import esan.mendoza.teststudyoso.presentation.viewModel.HorarioViewModelFactory
import esan.mendoza.teststudyoso.presentation.viewModel.TareaViewModel
import esan.mendoza.teststudyoso.presentation.viewModel.TareaViewModelFactory
import esan.mendoza.teststudyoso.presentation.viewModel.UsuarioViewModel
import esan.mendoza.teststudyoso.presentation.viewModel.UsuarioViewModelFactory
import esan.mendoza.teststudyoso.ui.theme.TestStudyOsoTheme

class MainActivity : ComponentActivity() {
    private val usuarioViewModel: UsuarioViewModel by viewModels { UsuarioViewModelFactory(this) }
    private val cursoViewModel: CursoViewModel by viewModels { CursoViewModelFactory(this) }
    private val tareaViewModel: TareaViewModel by viewModels { TareaViewModelFactory(this) }
    private val calificacionViewModel: CalificacionViewModel by viewModels { CalificacionViewModelFactory(this) }
    private val horarioViewModel: HorarioViewModel by viewModels { HorarioViewModelFactory(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestStudyOsoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavegacionApp(
                        usuarioViewModel,
                        cursoViewModel,
                        tareaViewModel,
                        calificacionViewModel,
                        horarioViewModel,
                    )
                }
            }
        }
    }
}