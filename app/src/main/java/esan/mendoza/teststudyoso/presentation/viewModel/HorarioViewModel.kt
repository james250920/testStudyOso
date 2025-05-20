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
import kotlinx.coroutines.launch

class HorarioViewModel(
    private val repository: HorarioRepository
) : ViewModel() {

    fun insert(horario: Horario) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(horario)
    }

    fun update(horario: Horario) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(horario)
    }

    fun delete(horario: Horario) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(horario)
    }

    fun getHorariosByCurso(idCurso: Int): LiveData<List<Horario>> {
        return repository.getHorariosByCurso(idCurso)
    }

    fun getHorariosByDia(diaSemana: String): LiveData<List<Horario>> {
        return repository.getHorariosByDia(diaSemana)
    }
}

class HorarioViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HorarioViewModel::class.java)) {
            val db = AppDatabase.getDatabase(context)
            @Suppress("UNCHECKED_CAST")
            return HorarioViewModel(HorarioRepository(context, db.horarioDao())) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}