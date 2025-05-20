package esan.mendoza.teststudyoso.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import esan.mendoza.teststudyoso.data.entity.Usuario



@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: Usuario)

    @Update
    suspend fun update(usuario: Usuario)

    @Delete
    suspend fun delete(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE id_usuario = :id")
    suspend fun getUsuarioById(id: Int): Usuario?

    @Query("SELECT * FROM usuarios WHERE correo = :correo")
    suspend fun getUsuarioByCorreo(correo: String): Usuario?

    @Query("SELECT * FROM usuarios")
    suspend fun getAllUsuarios(): List<Usuario>
    //login
    @Query("SELECT * FROM usuarios WHERE correo = :correo AND contrasena = :contrasena")
    suspend fun login(correo: String, contrasena: String): Usuario?
}