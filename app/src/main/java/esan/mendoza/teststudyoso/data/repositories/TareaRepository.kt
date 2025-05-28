package esan.mendoza.teststudyoso.data.repositories

import esan.mendoza.teststudyoso.data.dao.TareaDao
import esan.mendoza.teststudyoso.data.entities.Tarea
import kotlinx.coroutines.flow.Flow

class TareaRepository(private val dao: TareaDao) {
    fun getTareasByUsuario(userId: Int): Flow<List<Tarea>> = dao.getTareasByUsuario(userId)

    fun getTareasByCurso(cursoId: Int): Flow<List<Tarea>> = dao.getTareasByCurso(cursoId)

    suspend fun insert(tarea: Tarea) = dao.insert(tarea)

    suspend fun update(tarea: Tarea) = dao.update(tarea)

    suspend fun delete(tarea: Tarea) = dao.delete(tarea)
}