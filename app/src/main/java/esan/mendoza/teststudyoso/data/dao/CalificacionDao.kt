package esan.mendoza.teststudyoso.data.dao

import androidx.room.Dao
import androidx.room.*
import esan.mendoza.teststudyoso.data.entities.Calificacion
import kotlinx.coroutines.flow.Flow


@Dao
interface CalificacionDao {
    @Insert
    suspend fun insert(calificacion: Calificacion): Long

    @Update
    suspend fun update(calificacion: Calificacion)

    @Delete
    suspend fun delete(calificacion: Calificacion)

    @Query("SELECT * FROM Calificaciones WHERE id_curso = :cursoId")
    fun getCalificacionesByCurso(cursoId: Int): Flow<List<Calificacion>>

    @Query("SELECT * FROM Calificaciones WHERE id_tipo_prueba = :tipoPruebaId")
    fun getCalificacionesByTipoPrueba(tipoPruebaId: Int): Flow<List<Calificacion>>
}