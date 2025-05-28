package esan.mendoza.teststudyoso.ViewModel.curso

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import esan.mendoza.teststudyoso.data.entities.Curso
import esan.mendoza.teststudyoso.data.repositories.CursoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CursoViewModel(private val repository: CursoRepository) : ViewModel() {

    private val _cursos = MutableStateFlow<List<Curso>>(emptyList())
    val cursos: StateFlow<List<Curso>> = _cursos.asStateFlow()

    private val _cursoSeleccionado = MutableStateFlow<Curso?>(null)
    val cursoSeleccionado: StateFlow<Curso?> = _cursoSeleccionado.asStateFlow()

    // Carga los cursos de un usuario específico
    fun cargarCursos(usuarioId: Int) {
        viewModelScope.launch {
            repository.getCursosByUsuario(usuarioId).collect { lista ->
                _cursos.value = lista
            }
        }
    }

    fun agregarCurso(curso: Curso) {
        viewModelScope.launch {
            repository.insert(curso)
        }
    }

    fun actualizarCurso(curso: Curso) {
        viewModelScope.launch {
            repository.update(curso)
        }
    }

    fun eliminarCurso(curso: Curso) {
        viewModelScope.launch {
            repository.delete(curso)
        }
    }

    suspend fun getCursoById(id: Int): Curso? {
        val curso = repository.getCurso(id)
        _cursoSeleccionado.value = curso
        return curso
    }

}
