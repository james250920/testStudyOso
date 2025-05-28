package esan.mendoza.teststudyoso.ViewModel.tipoPrueba

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import esan.mendoza.teststudyoso.data.entities.TipoPrueba
import esan.mendoza.teststudyoso.data.repositories.TipoPruebaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TipoPruebaViewModel(private val repository: TipoPruebaRepository) : ViewModel() {

    private val _tiposPrueba = MutableStateFlow<List<TipoPrueba>>(emptyList())
    val tiposPrueba: StateFlow<List<TipoPrueba>> = _tiposPrueba.asStateFlow()

    // Carga tipos de prueba según el curso
    fun cargarTiposPorCurso(cursoId: Int) {
        viewModelScope.launch {
            repository.getTiposByCurso(cursoId).collect { lista ->
                _tiposPrueba.value = lista
            }
        }
    }

    fun agregarTipoPrueba(tipoPrueba: TipoPrueba) {
        viewModelScope.launch {
            repository.insert(tipoPrueba)
            cargarTiposPorCurso(tipoPrueba.idCurso)
        }
    }

    fun actualizarTipoPrueba(tipoPrueba: TipoPrueba) {
        viewModelScope.launch {
            repository.update(tipoPrueba)
        }
    }

    fun eliminarTipoPrueba(tipoPrueba: TipoPrueba) {
        viewModelScope.launch {
            repository.delete(tipoPrueba)
        }
    }
}