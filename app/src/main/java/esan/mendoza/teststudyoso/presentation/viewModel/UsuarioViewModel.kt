package esan.mendoza.teststudyoso.presentation.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import esan.mendoza.teststudyoso.data.db.AppDatabase
import esan.mendoza.teststudyoso.data.entity.Usuario
import esan.mendoza.teststudyoso.data.repository.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class UsuarioViewModel(
    private val repository: UsuarioRepository
) : ViewModel() {
    val allUsuarios: Flow<List<Usuario>> = repository.allUsuarios

    fun insert(usuario: Usuario) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(usuario)
    }

    fun update(usuario: Usuario) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(usuario)
    }

    fun delete(usuario: Usuario) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(usuario)
    }

    suspend fun getUsuarioById(id: Int): Usuario? {
        return repository.getUsuarioById(id)
    }

    suspend fun getUsuarioByCorreo(correo: String): Usuario? {
        return repository.getUsuarioByCorreo(correo)
    }

    fun getAllUsuariosLiveData(): LiveData<List<Usuario>> {
        return repository.getAllUsuariosLiveData()
    }
    suspend fun login(correo: String, contrasena: String): Usuario? {
        return repository.login(correo, contrasena)
    }
}

class UsuarioViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsuarioViewModel::class.java)) {
            val db = AppDatabase.getDatabase(context)
            @Suppress("UNCHECKED_CAST")
            return UsuarioViewModel(UsuarioRepository(context, db.usuarioDao())) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
