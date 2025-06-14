package esan.mendoza.teststudyoso.ViewModel.curso

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import esan.mendoza.teststudyoso.data.repositories.CursoRepository


class CursoViewModelFactory(private val repository: CursoRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CursoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CursoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}