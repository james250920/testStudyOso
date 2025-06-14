package esan.mendoza.teststudyoso.ViewModel.tarea

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import esan.mendoza.teststudyoso.data.repositories.TareaRepository

class TareaViewModelFactory(private val repository: TareaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TareaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TareaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
