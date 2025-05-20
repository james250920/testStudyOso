package esan.mendoza.teststudyoso.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import esan.mendoza.teststudyoso.data.entity.Tarea


@Dao
interface TareaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tarea: Tarea)

    @Update
    suspend fun update(tarea: Tarea)

    @Delete
    suspend fun delete(tarea: Tarea)

    @Query("SELECT * FROM tareas WHERE id_usuario = :idUsuario")
    suspend fun getTareasByUsuario(idUsuario: Int): List<Tarea>

    @Query("SELECT * FROM tareas WHERE id_curso = :idCurso")
    suspend fun getTareasByCurso(idCurso: Int?): List<Tarea>

    @Query("SELECT * FROM tareas WHERE estado = :estado")
    suspend fun getTareasByEstado(estado: String): List<Tarea>
}