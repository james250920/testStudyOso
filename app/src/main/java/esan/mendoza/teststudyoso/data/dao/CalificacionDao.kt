package esan.mendoza.teststudyoso.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import esan.mendoza.teststudyoso.data.entity.Calificacion


@Dao
interface CalificacionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarCalificacion(calificacion: Calificacion)

    @Query("SELECT * FROM calificacion WHERE idCurso = :idCurso AND idTipoPrueba = :idTipoPrueba")
    suspend fun obtenerCalificacionesPorCursoYTipo(idCurso: Int, idTipoPrueba: Int): List<Calificacion>

    @Delete
    suspend fun eliminarCalificacion(calificacion: Calificacion)
}