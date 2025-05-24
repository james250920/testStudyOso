package esan.mendoza.teststudyoso.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import esan.mendoza.teststudyoso.data.entity.Horario


@Dao
interface HorarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarHorario(horario: Horario)

    @Query("SELECT * FROM horario WHERE idCurso = :idCurso ORDER BY diaSemana, horaInicio")
    suspend fun obtenerHorariosPorCurso(idCurso: Int): List<Horario>

    @Delete
    suspend fun eliminarHorario(horario: Horario)
}