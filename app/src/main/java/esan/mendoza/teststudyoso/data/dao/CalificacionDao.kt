package esan.mendoza.teststudyoso.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import esan.mendoza.teststudyoso.data.entity.Calificacion


@Dao
interface CalificacionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(calificacion: Calificacion)

    @Update
    suspend fun update(calificacion: Calificacion)

    @Delete
    suspend fun delete(calificacion: Calificacion)

    @Query("SELECT * FROM calificaciones WHERE id_curso = :idCurso")
    suspend fun getCalificacionesByCurso(idCurso: Int): List<Calificacion>

    @Query("SELECT * FROM calificaciones WHERE id_tipo_prueba = :idTipoPrueba")
    suspend fun getCalificacionesByTipoPrueba(idTipoPrueba: Int): List<Calificacion>

    @Transaction
    @Query("SELECT * FROM calificaciones WHERE id_curso = :idCurso AND numero_prueba <= (SELECT cantidad_pruebas FROM tipos_prueba WHERE id_tipo_prueba = calificaciones.id_tipo_prueba)")
    suspend fun getValidCalificacionesByCurso(idCurso: Int): List<Calificacion>


}