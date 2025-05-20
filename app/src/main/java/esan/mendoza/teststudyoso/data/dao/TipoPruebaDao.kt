package esan.mendoza.teststudyoso.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import esan.mendoza.teststudyoso.data.entity.TipoPrueba

@Dao
interface TipoPruebaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tipoPrueba: TipoPrueba)

    @Update
    suspend fun update(tipoPrueba: TipoPrueba)

    @Delete
    suspend fun delete(tipoPrueba: TipoPrueba)

    @Query("SELECT * FROM tipos_prueba WHERE id_curso = :idCurso")
    suspend fun getTiposPruebaByCurso(idCurso: Int): List<TipoPrueba>

    @Query("SELECT * FROM tipos_prueba WHERE id_tipo_prueba = :id")
    suspend fun getTipoPruebaById(id: Int): TipoPrueba?
}