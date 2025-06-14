package esan.mendoza.teststudyoso.data.repositories

import esan.mendoza.teststudyoso.data.dao.CursoDao
import esan.mendoza.teststudyoso.data.entities.Curso
import kotlinx.coroutines.flow.Flow

class CursoRepository(private val dao: CursoDao) {
    fun getCursosByUsuario(userId: Int): Flow<List<Curso>> = dao.getCursosByUsuario(userId)

    suspend fun getCurso(id: Int): Curso? = dao.getById(id)

    suspend fun insert(curso: Curso) = dao.insert(curso)

    suspend fun update(curso: Curso) = dao.update(curso)

    suspend fun delete(curso: Curso) = dao.delete(curso)

    fun getCursos(): Flow<List<Curso>> {
        return dao.getCursos()
    }
}