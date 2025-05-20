package esan.mendoza.teststudyoso.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import esan.mendoza.teststudyoso.data.entity.Horario


@Dao
interface HorarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(horario: Horario)

    @Update
    suspend fun update(horario: Horario)

    @Delete
    suspend fun delete(horario: Horario)

    @Query("SELECT * FROM horarios WHERE id_curso = :idCurso")
    suspend fun getHorariosByCurso(idCurso: Int): List<Horario>

    @Query("SELECT * FROM horarios WHERE dia_semana = :diaSemana")
    suspend fun getHorariosByDia(diaSemana: String): List<Horario>
}