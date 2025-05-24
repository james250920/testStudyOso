package esan.mendoza.teststudyoso.presentation.viewModel
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import esan.mendoza.teststudyoso.data.db.AppDatabase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import esan.mendoza.teststudyoso.data.entity.Calificacion
import esan.mendoza.teststudyoso.data.repository.CalificacionRepository
import kotlinx.coroutines.Dispatchers

class CalificacionViewModel(private val repository: CalificacionRepository) : ViewModel() {

    private val _calificaciones = MutableStateFlow<List<Calificacion>>(emptyList())
    val calificaciones: StateFlow<List<Calificacion>> = _calificaciones.asStateFlow()

    fun cargarCalificaciones(idCurso: Int, idTipoPrueba: Int) = viewModelScope.launch {
        val lista = repository.obtenerPorCursoYTipo(idCurso, idTipoPrueba)
        _calificaciones.value = lista
    }

    fun insertarCalificacion(calificacion: Calificacion) = viewModelScope.launch {
        repository.insertar(calificacion)
        cargarCalificaciones(calificacion.idCurso, calificacion.idTipoPrueba)
    }

    fun eliminarCalificacion(calificacion: Calificacion) = viewModelScope.launch {
        repository.eliminar(calificacion)
        cargarCalificaciones(calificacion.idCurso, calificacion.idTipoPrueba)
    }
}
