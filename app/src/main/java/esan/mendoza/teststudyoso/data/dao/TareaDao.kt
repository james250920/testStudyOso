package esan.mendoza.teststudyoso.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import esan.mendoza.teststudyoso.data.entity.Tarea


@Dao
interface TareaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTarea(tarea: Tarea)

    @Query("SELECT * FROM tarea WHERE idUsuario = :idUsuario ORDER BY fechaVencimiento ASC")
    suspend fun obtenerTareasPorUsuario(idUsuario: Int): List<Tarea>

    @Delete
    suspend fun eliminarTarea(tarea: Tarea)
}