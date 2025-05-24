package esan.mendoza.teststudyoso.data.repository

import esan.mendoza.teststudyoso.data.dao.HorarioDao
import esan.mendoza.teststudyoso.data.entity.Horario



class HorarioRepository(private val dao: HorarioDao) {

    suspend fun insertar(horario: Horario) = dao.insertarHorario(horario)

    suspend fun obtenerPorCurso(idCurso: Int): List<Horario> = dao.obtenerHorariosPorCurso(idCurso)

    suspend fun eliminar(horario: Horario) = dao.eliminarHorario(horario)
}