package esan.mendoza.teststudyoso.data.dao


import androidx.room.Dao
import androidx.room.*
import esan.mendoza.teststudyoso.data.entities.Curso
import kotlinx.coroutines.flow.Flow

@Dao
interface CursoDao {
    @Insert
    suspend fun insert(curso: Curso): Long

    @Update
    suspend fun update(curso: Curso)

    @Delete
    suspend fun delete(curso: Curso)

    @Query("SELECT * FROM Cursos WHERE id_usuario = :userId")
    fun getCursosByUsuario(userId: Int): Flow<List<Curso>>

    @Query("SELECT * FROM Cursos WHERE id_curso = :id")
    suspend fun getById(id: Int): Curso?

    //implemtar getCursos
    @Query("SELECT * FROM Cursos")
    fun getCursos(): Flow<List<Curso>>

}