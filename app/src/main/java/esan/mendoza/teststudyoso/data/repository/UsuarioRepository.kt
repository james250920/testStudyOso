package esan.mendoza.teststudyoso.data.repository


import esan.mendoza.teststudyoso.data.dao.UsuarioDao
import esan.mendoza.teststudyoso.data.entity.Usuario



class UsuarioRepository(private val dao: UsuarioDao) {

    suspend fun registrar(usuario: Usuario): Result<Unit> = try {
        dao.insertarUsuario(usuario)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun existeCorreo(correo: String): Boolean =
        dao.obtenerPorCorreo(correo) != null

    suspend fun iniciarSesion(correo: String, contrasena: String): Result<Usuario> {
        val usuario = dao.iniciarSesion(correo, contrasena)
        return if (usuario != null) Result.success(usuario)
        else Result.failure(Exception("Credenciales inválidas"))
    }
}


