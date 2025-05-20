package esan.mendoza.teststudyoso.data.repository


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import esan.mendoza.teststudyoso.data.dao.CalificacionDao
import esan.mendoza.teststudyoso.data.dao.TipoPruebaDao
import esan.mendoza.teststudyoso.data.entity.Calificacion
import esan.mendoza.teststudyoso.di.ValidationUtils
import kotlinx.coroutines.flow.flow

class CalificacionRepository(
    context: Context,
    private val calificacionDao: CalificacionDao,
    private val tipoPruebaDao: TipoPruebaDao // Necesario para validar numeroPrueba){}
) {
    suspend fun insert(calificacion: Calificacion) {
        if (!ValidationUtils.isValidNumeroPrueba(calificacion.numeroPrueba, calificacion.idTipoPrueba, tipoPruebaDao)) {
            throw IllegalArgumentException("Número de prueba excede cantidad_pruebas")
        }
        if (calificacion.calificacionObtenida != null && (calificacion.calificacionObtenida < 0 || calificacion.calificacionObtenida > 10)) {
            throw IllegalArgumentException("La calificación debe estar entre 0 y 10")
        }
        calificacionDao.insert(calificacion)
    }

    suspend fun update(calificacion: Calificacion) {
        calificacionDao.update(calificacion)
    }

    suspend fun delete(calificacion: Calificacion) {
        calificacionDao.delete(calificacion)
    }

    fun getCalificacionesByCurso(idCurso: Int): LiveData<List<Calificacion>> {
        return flow { emit(calificacionDao.getValidCalificacionesByCurso(idCurso)) }.asLiveData()
    }

    fun getCalificacionesByTipoPrueba(idTipoPrueba: Int): LiveData<List<Calificacion>> {
        return flow { emit(calificacionDao.getCalificacionesByTipoPrueba(idTipoPrueba)) }.asLiveData()
    }
}