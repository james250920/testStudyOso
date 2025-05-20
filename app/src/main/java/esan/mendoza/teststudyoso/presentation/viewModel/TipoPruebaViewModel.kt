package esan.mendoza.teststudyoso.presentation.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import esan.mendoza.teststudyoso.data.db.AppDatabase
import esan.mendoza.teststudyoso.data.entity.TipoPrueba
import esan.mendoza.teststudyoso.data.repository.TipoPruebaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TipoPruebaViewModel(
    private val repository: TipoPruebaRepository
) : ViewModel() {

    fun insert(tipoPrueba: TipoPrueba) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(tipoPrueba)
    }

    fun update(tipoPrueba: TipoPrueba) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(tipoPrueba)
    }

    fun delete(tipoPrueba: TipoPrueba) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(tipoPrueba)
    }

    fun getTiposPruebaByCurso(idCurso: Int): LiveData<List<TipoPrueba>> {
        return repository.getTiposPruebaByCurso(idCurso)
    }

    suspend fun getTipoPruebaById(id: Int): TipoPrueba? {
        return repository.getTipoPruebaById(id)
    }
}

class TipoPruebaViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TipoPruebaViewModel::class.java)) {
            val db = AppDatabase.getDatabase(context)
            @Suppress("UNCHECKED_CAST")
            return TipoPruebaViewModel(TipoPruebaRepository(context, db.tipoPruebaDao())) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
