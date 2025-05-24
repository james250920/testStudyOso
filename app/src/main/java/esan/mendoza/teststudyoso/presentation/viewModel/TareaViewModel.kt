package esan.mendoza.teststudyoso.presentation.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import esan.mendoza.teststudyoso.data.db.AppDatabase
import esan.mendoza.teststudyoso.data.entity.Tarea
import esan.mendoza.teststudyoso.data.repository.TareaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class TareaViewModel(private val repository: TareaRepository) : ViewModel() {

    private val _tareas = MutableStateFlow<List<Tarea>>(emptyList())
    val tareas: StateFlow<List<Tarea>> = _tareas.asStateFlow()

    fun cargarTareas(idUsuario: Int) = viewModelScope.launch {
        val lista = repository.obtenerPorUsuario(idUsuario)
        _tareas.value = lista
    }

    fun insertarTarea(tarea: Tarea) = viewModelScope.launch {
        repository.insertar(tarea)
        cargarTareas(tarea.idUsuario)
    }

    fun eliminarTarea(tarea: Tarea) = viewModelScope.launch {
        repository.eliminar(tarea)
        cargarTareas(tarea.idUsuario)
    }
}
