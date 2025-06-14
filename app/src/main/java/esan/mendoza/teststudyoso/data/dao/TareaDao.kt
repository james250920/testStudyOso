package esan.mendoza.teststudyoso.data.dao

import androidx.room.*
import esan.mendoza.teststudyoso.data.entities.Tarea
import kotlinx.coroutines.flow.Flow

@Dao
interface TareaDao {
    @Insert
    suspend fun insert(tarea: Tarea): Long

    @Update
    suspend fun update(tarea: Tarea)

    @Delete
    suspend fun delete(tarea: Tarea)

    @Query("SELECT * FROM Tareas WHERE id_usuario = :userId")
    fun getTareasByUsuario(userId: Int): Flow<List<Tarea>>

    @Query("SELECT * FROM Tareas WHERE id_curso = :cursoId")
    fun getTareasByCurso(cursoId: Int): Flow<List<Tarea>>

    @Query("SELECT * FROM Tareas")
    suspend fun getAllTareas(): List<Tarea>
}