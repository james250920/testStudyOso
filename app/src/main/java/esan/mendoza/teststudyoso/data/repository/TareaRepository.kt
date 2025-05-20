package esan.mendoza.teststudyoso.data.repository


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import esan.mendoza.teststudyoso.data.dao.TareaDao
import esan.mendoza.teststudyoso.data.entity.Tarea
import esan.mendoza.teststudyoso.di.ValidationUtils
import kotlinx.coroutines.flow.flow


class TareaRepository(
    context: Context,
    private val tareaDao: TareaDao
) {
    suspend fun insert(tarea: Tarea) {
        if (tarea.descripcion.isBlank()) {
            throw IllegalArgumentException("La descripción de la tarea es obligatoria")
        }
        if (!ValidationUtils.isValidEstado(tarea.estado)) {
            throw IllegalArgumentException("Estado inválido")
        }
        tareaDao.insert(tarea)
    }

    suspend fun update(tarea: Tarea) {
        tareaDao.update(tarea)
    }

    suspend fun delete(tarea: Tarea) {
        tareaDao.delete(tarea)
    }

    fun getTareasByUsuario(idUsuario: Int): LiveData<List<Tarea>> {
        return flow { emit(tareaDao.getTareasByUsuario(idUsuario)) }.asLiveData()
    }

    fun getTareasByCurso(idCurso: Int?): LiveData<List<Tarea>> {
        return flow { emit(tareaDao.getTareasByCurso(idCurso)) }.asLiveData()
    }

    fun getTareasByEstado(estado: String): LiveData<List<Tarea>> {
        if (!ValidationUtils.isValidEstado(estado)) {
            throw IllegalArgumentException("Estado inválido")
        }
        return flow { emit(tareaDao.getTareasByEstado(estado)) }.asLiveData()
    }
}