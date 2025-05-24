package esan.mendoza.teststudyoso.presentation.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import esan.mendoza.teststudyoso.data.entity.TipoPrueba
import esan.mendoza.teststudyoso.data.repository.TipoPruebaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class TipoPruebaViewModel(private val repository: TipoPruebaRepository) : ViewModel() {

    private val _tiposPrueba = MutableStateFlow<List<TipoPrueba>>(emptyList())
    val tiposPrueba: StateFlow<List<TipoPrueba>> = _tiposPrueba.asStateFlow()

    fun cargarTipos(idCurso: Int) = viewModelScope.launch {
        val lista = repository.obtenerPorCurso(idCurso)
        _tiposPrueba.value = lista
    }

    fun insertarTipo(tipoPrueba: TipoPrueba) = viewModelScope.launch {
        repository.insertar(tipoPrueba)
        cargarTipos(tipoPrueba.idTipoPrueba)
    }

    fun eliminarTipo(tipoPrueba: TipoPrueba) = viewModelScope.launch {
        repository.eliminar(tipoPrueba)
        cargarTipos(tipoPrueba.idTipoPrueba)
    }
}

