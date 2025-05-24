package esan.mendoza.teststudyoso.presentation.viewModel
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import esan.mendoza.teststudyoso.data.db.AppDatabase

import esan.mendoza.teststudyoso.data.entity.Curso
import esan.mendoza.teststudyoso.data.repository.CursoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CursoViewModel(private val repository: CursoRepository) : ViewModel() {

    private val _cursos = MutableStateFlow<List<Curso>>(emptyList())
    val cursos: StateFlow<List<Curso>> = _cursos.asStateFlow()

    fun cargarCursos(idUsuario: Int) = viewModelScope.launch {
        val lista = repository.obtenerCursosPorUsuario(idUsuario)
        _cursos.value = lista
    }

    fun insertarCurso(curso: Curso) = viewModelScope.launch {
        repository.insertarCurso(curso)
        // Opcional: refrescar lista después de insertar
        cargarCursos(curso.idUsuario)
    }

    fun eliminarCurso(curso: Curso) = viewModelScope.launch {
        repository.eliminarCurso(curso)
        cargarCursos(curso.idUsuario)
    }
}
