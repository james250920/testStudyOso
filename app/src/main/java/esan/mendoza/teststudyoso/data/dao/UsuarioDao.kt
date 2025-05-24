package esan.mendoza.teststudyoso.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import esan.mendoza.teststudyoso.data.entity.Usuario



@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertarUsuario(usuario: Usuario)

    @Query("SELECT * FROM usuario WHERE correo = :correo LIMIT 1")
    suspend fun obtenerPorCorreo(correo: String): Usuario?

    @Query("SELECT * FROM usuario WHERE correo = :correo AND contrasena = :contrasena LIMIT 1")
    suspend fun iniciarSesion(correo: String, contrasena: String): Usuario?
}