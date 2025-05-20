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

class CursoViewModel(
    private val repository: CursoRepository
) : ViewModel() {

    fun insert(curso: Curso) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(
            curso
        )
    }

    fun update(curso: Curso) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(curso)
    }

    fun delete(curso: Curso) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(curso)
    }

    fun getCursosByUsuario(idUsuario: Int): LiveData<List<Curso>> {
        return repository.getCursosByUsuario(idUsuario)
    }

    suspend fun getCursoById(id: Int): Curso? {
        return repository.getCursoById(id)
    }
}

class CursoViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CursoViewModel::class.java)) {
            val db = AppDatabase.getDatabase(context)
            @Suppress("UNCHECKED_CAST")
            return CursoViewModel(CursoRepository(context, db.cursoDao())) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}