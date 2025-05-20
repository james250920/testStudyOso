package esan.mendoza.teststudyoso.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import esan.mendoza.teststudyoso.data.dao.UsuarioDao
import esan.mendoza.teststudyoso.data.entity.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext


class UsuarioRepository(
    context: Context,
    private val usuarioDao: UsuarioDao
) {
    val allUsuarios: Flow<List<Usuario>> = flow {
        emit(usuarioDao.getAllUsuarios())
    }

    suspend fun insert(usuario: Usuario) {
        if (usuario.correo.isBlank() || usuario.nombre.isBlank() || usuario.apellido.isBlank()) {
            throw IllegalArgumentException("Nombre, apellido y correo son obligatorios")
        }
        usuarioDao.insert(usuario)
    }

    suspend fun update(usuario: Usuario) {
        usuarioDao.update(usuario)
    }

    suspend fun delete(usuario: Usuario) {
        usuarioDao.delete(usuario)
    }

    suspend fun getUsuarioById(id: Int): Usuario? {
        return usuarioDao.getUsuarioById(id)
    }

    suspend fun getUsuarioByCorreo(correo: String): Usuario? {
        return usuarioDao.getUsuarioByCorreo(correo)
    }

    fun getAllUsuariosLiveData(): LiveData<List<Usuario>> {
        return allUsuarios.asLiveData()
    }
    suspend fun login(correo: String, contrasena: String): Usuario? {
        return withContext(Dispatchers.IO) {
            usuarioDao.login(correo, contrasena)
        }
    }
}


