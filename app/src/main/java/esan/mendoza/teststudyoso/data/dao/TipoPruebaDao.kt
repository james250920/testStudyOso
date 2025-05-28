package esan.mendoza.teststudyoso.data.dao

import androidx.room.Dao
import androidx.room.*
import esan.mendoza.teststudyoso.data.entities.TipoPrueba
import kotlinx.coroutines.flow.Flow


@Dao
interface TipoPruebaDao {
    @Insert
    suspend fun insert(tipoPrueba: TipoPrueba): Long

    @Update
    suspend fun update(tipoPrueba: TipoPrueba)

    @Delete
    suspend fun delete(tipoPrueba: TipoPrueba)

    @Query("SELECT * FROM Tipos_Prueba WHERE id_curso = :cursoId")
    fun getTiposByCurso(cursoId: Int): Flow<List<TipoPrueba>>
}