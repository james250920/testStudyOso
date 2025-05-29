package esan.mendoza.teststudyoso.ViewModel.tarea

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import esan.mendoza.teststudyoso.data.entities.Tarea
import esan.mendoza.teststudyoso.data.repositories.TareaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TareaViewModel(private val repository: TareaRepository) : ViewModel() {

    // Estado para la lista de tareas del usuario
    private val _tareas = MutableStateFlow<List<Tarea>>(emptyList())
    val tareas: StateFlow<List<Tarea>> = _tareas.asStateFlow()

    // Carga las tareas del usuario
    fun cargarTareas(usuarioId: Int) {
        viewModelScope.launch {
            repository.getTareasByUsuario(usuarioId)
                .collect { lista ->
                    _tareas.value = lista
                }
        }
    }

    suspend fun agregarTarea(tarea: Tarea) = repository.insert(tarea)
    suspend fun actualizarTarea(tarea: Tarea) = repository.update(tarea)
    suspend fun eliminarTarea(tarea: Tarea) = repository.delete(tarea)
}