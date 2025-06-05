package esan.mendoza.teststudyoso.ViewModel.Horario

import androidx.lifecycle.ViewModel
import esan.mendoza.teststudyoso.data.entities.Horario
import esan.mendoza.teststudyoso.data.repositories.HorarioRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HorarioViewModel(private val repository: HorarioRepository) : ViewModel() {

    private val _horarios = MutableStateFlow<List<Horario>>(emptyList())
    val horarios: StateFlow<List<Horario>> = _horarios.asStateFlow()

    // Cargar horarios para un curso específico
    fun cargarHorarios(cursoId: Int) {
        viewModelScope.launch {
            repository.getHorariosByCurso(cursoId).collect { lista ->
                _horarios.value = lista
            }
        }
    }

    fun agregarHorario(horario: Horario) {
        viewModelScope.launch {
            repository.insert(horario)
        }
    }

    fun actualizarHorario(horario: Horario) {
        viewModelScope.launch {
            repository.update(horario)
        }
    }

    fun eliminarHorario(horario: Horario) {
        viewModelScope.launch {
            repository.delete(horario)
        }
    }

}