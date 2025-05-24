package esan.mendoza.teststudyoso.presentation.viewModel
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import esan.mendoza.teststudyoso.data.db.AppDatabase
import esan.mendoza.teststudyoso.data.entity.Horario
import esan.mendoza.teststudyoso.data.repository.HorarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HorarioViewModel(private val repository: HorarioRepository) : ViewModel() {

    private val _horarios = MutableStateFlow<List<Horario>>(emptyList())
    val horarios: StateFlow<List<Horario>> = _horarios.asStateFlow()

    fun cargarHorarios(idCurso: Int) = viewModelScope.launch {
        val lista = repository.obtenerPorCurso(idCurso)
        _horarios.value = lista
    }

    fun insertarHorario(horario: Horario) = viewModelScope.launch {
        repository.insertar(horario)
        cargarHorarios(horario.idCurso)
    }

    fun eliminarHorario(horario: Horario) = viewModelScope.launch {
        repository.eliminar(horario)
        cargarHorarios(horario.idCurso)
    }
}
