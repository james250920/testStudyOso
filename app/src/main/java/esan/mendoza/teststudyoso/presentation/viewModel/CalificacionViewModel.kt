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

class CalificacionViewModel(
    private val repository: CalificacionRepository
) : ViewModel() {
    fun insert(calificacion: Calificacion) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(calificacion)
    }

    fun update(calificacion: Calificacion) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(calificacion)
    }

    fun delete(calificacion: Calificacion) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(calificacion)
    }

    fun getCalificacionesByCurso(idCurso: Int): LiveData<List<Calificacion>> {
        return repository.getCalificacionesByCurso(idCurso)
    }

    fun getCalificacionesByTipoPrueba(idTipoPrueba: Int): LiveData<List<Calificacion>> {
        return repository.getCalificacionesByTipoPrueba(idTipoPrueba)
    }
}

class CalificacionViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalificacionViewModel::class.java)) {
            val db = AppDatabase.getDatabase(context)
            @Suppress("UNCHECKED_CAST")
            return CalificacionViewModel(CalificacionRepository(context, db.calificacionDao(), db.tipoPruebaDao())) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}