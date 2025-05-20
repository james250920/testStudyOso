package esan.mendoza.teststudyoso.data.repository


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import esan.mendoza.teststudyoso.data.dao.TipoPruebaDao
import esan.mendoza.teststudyoso.data.entity.TipoPrueba
import kotlinx.coroutines.flow.flow

class TipoPruebaRepository(
    context: Context,
    private val tipoPruebaDao: TipoPruebaDao
) {
    suspend fun insert(tipoPrueba: TipoPrueba) {
        if (tipoPrueba.nombreTipo.isBlank()) {
            throw IllegalArgumentException("El nombre del tipo de prueba es obligatorio")
        }
        if (tipoPrueba.cantidadPruebas <= 0) {
            throw IllegalArgumentException("La cantidad de pruebas debe ser mayor a 0")
        }
        if (tipoPrueba.pesoTotal < 0 || tipoPrueba.pesoTotal > 1) {
            throw IllegalArgumentException("El peso total debe estar entre 0 y 1")
        }
        tipoPruebaDao.insert(tipoPrueba)
    }

    suspend fun update(tipoPrueba: TipoPrueba) {
        tipoPruebaDao.update(tipoPrueba)
    }

    suspend fun delete(tipoPrueba: TipoPrueba) {
        tipoPruebaDao.delete(tipoPrueba)
    }

    fun getTiposPruebaByCurso(idCurso: Int): LiveData<List<TipoPrueba>> {
        return flow { emit(tipoPruebaDao.getTiposPruebaByCurso(idCurso)) }.asLiveData()
    }

    suspend fun getTipoPruebaById(id: Int): TipoPrueba? {
        return tipoPruebaDao.getTipoPruebaById(id)
    }
}