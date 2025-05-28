package esan.mendoza.teststudyoso.ViewModel.calificacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import esan.mendoza.teststudyoso.data.entities.Calificacion
import esan.mendoza.teststudyoso.data.repositories.CalificacionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CalificacionViewModel(private val repository: CalificacionRepository) : ViewModel() {

    // Estado para las calificaciones de un curso
    private val _calificaciones = MutableStateFlow<List<Calificacion>>(emptyList())
    val calificaciones: StateFlow<List<Calificacion>> = _calificaciones.asStateFlow()

    // Cargar calificaciones por curso, actualiza el flujo
    fun cargarCalificacionesPorCurso(cursoId: Int) {
        viewModelScope.launch {
            repository.getCalificacionesByCurso(cursoId).collect { lista ->
                _calificaciones.value = lista
            }
        }

    }

    // Insertar nueva calificación de forma asíncrona
    fun insertarCalificacion(calificacion: Calificacion) {
        viewModelScope.launch {
            repository.insert(calificacion)
        }
    }

    // Actualizar calificación existente
    fun actualizarCalificacion(calificacion: Calificacion) {
        viewModelScope.launch {
            repository.update(calificacion)
        }
    }

    // Eliminar calificación
    fun eliminarCalificacion(calificacion: Calificacion) {
        viewModelScope.launch {
            repository.delete(calificacion)
        }
    }
}