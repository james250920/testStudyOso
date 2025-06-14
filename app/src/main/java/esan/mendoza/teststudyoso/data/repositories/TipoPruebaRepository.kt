package esan.mendoza.teststudyoso.data.repositories

import esan.mendoza.teststudyoso.data.dao.TipoPruebaDao
import esan.mendoza.teststudyoso.data.entities.TipoPrueba
import esan.mendoza.teststudyoso.domain.CursoConTiposPrueba
import kotlinx.coroutines.flow.Flow

class TipoPruebaRepository(private val dao: TipoPruebaDao) {
    fun getTiposByCurso(cursoId: Int): Flow<List<TipoPrueba>> = dao.getTiposByCurso(cursoId)

    suspend fun insert(tipoPrueba: TipoPrueba) = dao.insert(tipoPrueba)

    suspend fun update(tipoPrueba: TipoPrueba) = dao.update(tipoPrueba)

    suspend fun delete(tipoPrueba: TipoPrueba) = dao.delete(tipoPrueba)

    fun getTiposPruebaByCurso(cursoId: Int): Flow<List<CursoConTiposPrueba>> {
        return dao.getTiposPruebaByCurso(cursoId)
    }
}