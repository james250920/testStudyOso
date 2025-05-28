package esan.mendoza.teststudyoso.data.repositories

import esan.mendoza.teststudyoso.data.dao.HorarioDao
import esan.mendoza.teststudyoso.data.entities.Horario
import kotlinx.coroutines.flow.Flow

class HorarioRepository(private val dao: HorarioDao) {
    fun getHorariosByCurso(cursoId: Int): Flow<List<Horario>> = dao.getHorariosByCurso(cursoId)

    suspend fun insert(horario: Horario) = dao.insert(horario)

    suspend fun update(horario: Horario) = dao.update(horario)

    suspend fun delete(horario: Horario) = dao.delete(horario)
}