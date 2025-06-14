package esan.mendoza.teststudyoso.data.repositories

import esan.mendoza.teststudyoso.data.dao.UsuarioDao
import esan.mendoza.teststudyoso.data.entities.Usuario
import kotlinx.coroutines.flow.Flow

class UsuarioRepository(private val dao: UsuarioDao) {
    val usuarios: Flow<List<Usuario>> = dao.getAll()

    suspend fun getUsuario(id: Int): Usuario? = dao.getById(id)

    suspend fun insert(usuario: Usuario) = dao.insert(usuario)

    suspend fun update(usuario: Usuario) = dao.update(usuario)

    suspend fun delete(usuario: Usuario) = dao.delete(usuario)

    suspend fun login(correo: String, contrasena: String): Usuario? {
        return dao.login(correo, contrasena)
    }
    suspend fun getUsuarioAutenticado(id: Int): Usuario? {
        return dao.getUsuarioAutenticado(id)
    }
}