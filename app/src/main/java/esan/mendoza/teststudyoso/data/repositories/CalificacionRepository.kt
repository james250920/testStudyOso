package esan.mendoza.teststudyoso.data.repositories

import esan.mendoza.teststudyoso.data.dao.CalificacionDao
import esan.mendoza.teststudyoso.data.entities.Calificacion
import kotlinx.coroutines.flow.Flow

class CalificacionRepository(private val dao: CalificacionDao) {
    fun getCalificacionesByCurso(cursoId: Int): Flow<List<Calificacion>> = dao.getCalificacionesByCurso(cursoId)

    fun getCalificacionesByTipoPrueba(tipoPruebaId: Int): Flow<List<Calificacion>> = dao.getCalificacionesByTipoPrueba(tipoPruebaId)

    suspend fun insert(calificacion: Calificacion) = dao.insert(calificacion)

    suspend fun update(calificacion: Calificacion) = dao.update(calificacion)

    suspend fun delete(calificacion: Calificacion) = dao.delete(calificacion)
}