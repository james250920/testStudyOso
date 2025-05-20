package esan.mendoza.teststudyoso.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import esan.mendoza.teststudyoso.data.dao.HorarioDao
import esan.mendoza.teststudyoso.data.entity.Horario
import esan.mendoza.teststudyoso.di.ValidationUtils
import kotlinx.coroutines.flow.flow


class HorarioRepository(
    private val context: Context,
    private val horarioDao: HorarioDao,

) {
    suspend fun insert(horario: Horario) {
        if (!ValidationUtils.isValidDiaSemana(horario.diaSemana)) {
            throw IllegalArgumentException("Día de la semana inválido")
        }
        if (!ValidationUtils.isValidTimeFormat(horario.horaInicio) || !ValidationUtils.isValidTimeFormat(horario.horaFin)) {
            throw IllegalArgumentException("Formato de hora inválido")
        }
        horarioDao.insert(horario)
    }

    suspend fun update(horario: Horario) {
        horarioDao.update(horario)
    }

    suspend fun delete(horario: Horario) {
        horarioDao.delete(horario)
    }

    fun getHorariosByCurso(idCurso: Int): LiveData<List<Horario>> {
        return flow { emit(horarioDao.getHorariosByCurso(idCurso)) }.asLiveData()
    }

    fun getHorariosByDia(diaSemana: String): LiveData<List<Horario>> {
        if (!ValidationUtils.isValidDiaSemana(diaSemana)) {
            throw IllegalArgumentException("Día de la semana inválido")
        }
        return flow { emit(horarioDao.getHorariosByDia(diaSemana)) }.asLiveData()
    }
}