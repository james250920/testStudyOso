package esan.mendoza.teststudyoso.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import esan.mendoza.teststudyoso.data.entity.Curso


@Dao
interface CursoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(curso: Curso)

    @Update
    suspend fun update(curso: Curso)

    @Delete
    suspend fun delete(curso: Curso)

    @Query("SELECT * FROM cursos WHERE id_usuario = :idUsuario")
    suspend fun getCursosByUsuario(idUsuario: Int): List<Curso>

    @Query("SELECT * FROM cursos WHERE id_curso = :id")
    suspend fun getCursoById(id: Int): Curso?
}
