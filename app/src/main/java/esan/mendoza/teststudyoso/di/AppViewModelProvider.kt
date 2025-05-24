package esan.mendoza.teststudyoso.di

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import esan.mendoza.teststudyoso.MainApplication
import esan.mendoza.teststudyoso.data.repository.CalificacionRepository
import esan.mendoza.teststudyoso.data.repository.CursoRepository
import esan.mendoza.teststudyoso.data.repository.HorarioRepository
import esan.mendoza.teststudyoso.data.repository.TareaRepository
import esan.mendoza.teststudyoso.data.repository.TipoPruebaRepository
import esan.mendoza.teststudyoso.data.repository.UsuarioRepository
import esan.mendoza.teststudyoso.presentation.viewModel.CalificacionViewModel
import esan.mendoza.teststudyoso.presentation.viewModel.CursoViewModel
import esan.mendoza.teststudyoso.presentation.viewModel.HorarioViewModel
import esan.mendoza.teststudyoso.presentation.viewModel.TareaViewModel
import esan.mendoza.teststudyoso.presentation.viewModel.TipoPruebaViewModel
import esan.mendoza.teststudyoso.presentation.viewModel.UsuarioViewModel

object AppViewModelProvider {

    val UsuarioViewModelFactory = viewModelFactory {
        initializer {
            val usuarioDao = MainApplication.appDatabase.usuarioDao()
            val repository = UsuarioRepository(usuarioDao)
            UsuarioViewModel(repository)
        }
    }

    val CursoViewModelFactory = viewModelFactory {
        initializer {
            val cursoDao = MainApplication.appDatabase.cursoDao()
            val repository = CursoRepository(cursoDao)
            CursoViewModel(repository)
        }
    }

    val TipoPruebaViewModelFactory = viewModelFactory {
        initializer {
            val tipoPruebaDao = MainApplication.appDatabase.tipoPruebaDao()
            val repository = TipoPruebaRepository(tipoPruebaDao)
            TipoPruebaViewModel(repository)
        }
    }

    val TareaViewModelFactory = viewModelFactory {
        initializer {
            val tareaDao = MainApplication.appDatabase.tareaDao()
            val repository = TareaRepository(tareaDao)
            TareaViewModel(repository)
        }
    }

    val HorarioViewModelFactory = viewModelFactory {
        initializer {
            val horarioDao = MainApplication.appDatabase.horarioDao()
            val repository = HorarioRepository(horarioDao)
            HorarioViewModel(repository)
        }
    }

    val CalificacionViewModelFactory = viewModelFactory {
        initializer {
            val calificacionDao = MainApplication.appDatabase.calificacionDao()
            val repository = CalificacionRepository(calificacionDao)
            CalificacionViewModel(repository)
        }
    }
}

