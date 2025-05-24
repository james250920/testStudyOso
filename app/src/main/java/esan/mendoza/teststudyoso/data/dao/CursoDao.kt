package esan.mendoza.teststudyoso.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


import esan.mendoza.teststudyoso.data.entity.Curso


@Dao
interface CursoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarCurso(curso: Curso)

    @Query("SELECT * FROM curso WHERE idUsuario = :idUsuario")
    suspend fun obtenerCursosPorUsuario(idUsuario: Int): List<Curso>

    @Delete
    suspend fun eliminarCurso(curso: Curso)
}