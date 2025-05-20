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
import kotlinx.coroutines.launch


class TareaViewModel(
    private val repository: TareaRepository
) : ViewModel() {

    fun insert(tarea: Tarea) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(tarea)
    }

    fun update(tarea: Tarea) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(tarea)
    }

    fun delete(tarea: Tarea) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(tarea)
    }

    fun getTareasByUsuario(idUsuario: Int): LiveData<List<Tarea>> {
        return repository.getTareasByUsuario(idUsuario)
    }

    fun getTareasByCurso(idCurso: Int?): LiveData<List<Tarea>> {
        return repository.getTareasByCurso(idCurso)
    }

    fun getTareasByEstado(estado: String): LiveData<List<Tarea>> {
        return repository.getTareasByEstado(estado)
    }
}

class TareaViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TareaViewModel::class.java)) {
            val db = AppDatabase.getDatabase(context)
            @Suppress("UNCHECKED_CAST")
            return TareaViewModel(TareaRepository(context, db.tareaDao())) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
