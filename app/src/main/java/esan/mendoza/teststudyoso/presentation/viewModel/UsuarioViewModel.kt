package esan.mendoza.teststudyoso.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import esan.mendoza.teststudyoso.data.entity.Usuario
import esan.mendoza.teststudyoso.data.repository.UsuarioRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class UsuarioViewModel(private val repository: UsuarioRepository) : ViewModel() {

    private val _usuarioState = MutableStateFlow<Usuario?>(null)
    val usuarioState: StateFlow<Usuario?> = _usuarioState.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun registrar(
        nombre: String,
        apellido: String,
        fechaNacimiento: String,
        correo: String,
        contrasena: String
    ) = viewModelScope.launch {
        val nuevoUsuario = Usuario(
            nombre = nombre,
            apellido = apellido,
            fechaNacimiento = fechaNacimiento,
            correo = correo,
            contrasena = contrasena
        )
        val result = repository.registrar(nuevoUsuario)
        if (result.isFailure) {
            _error.value = result.exceptionOrNull()?.message
        }
    }

    fun iniciarSesion(correo: String, contrasena: String) = viewModelScope.launch {
        val result = repository.iniciarSesion(correo, contrasena)
        if (result.isSuccess) {
            _usuarioState.value = result.getOrNull()
        } else {
            _error.value = "Credenciales inválidas"
        }
    }

    fun existeCorreo(correo: String) = liveData {
        emit(repository.existeCorreo(correo))
    }
}

