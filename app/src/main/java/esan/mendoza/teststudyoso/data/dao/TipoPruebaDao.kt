package esan.mendoza.teststudyoso.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import esan.mendoza.teststudyoso.data.entity.TipoPrueba

@Dao
interface TipoPruebaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTipoPrueba(tipoPrueba: TipoPrueba)

    @Query("SELECT * FROM tipoPrueba WHERE idCurso = :idCurso")
    suspend fun obtenerTiposPorCurso(idCurso: Int): List<TipoPrueba>

    @Delete
    suspend fun eliminarTipoPrueba(tipoPrueba: TipoPrueba)
}