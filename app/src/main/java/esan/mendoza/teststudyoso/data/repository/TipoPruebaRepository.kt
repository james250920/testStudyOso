package esan.mendoza.teststudyoso.data.repository


import esan.mendoza.teststudyoso.data.dao.TipoPruebaDao
import esan.mendoza.teststudyoso.data.entity.TipoPrueba


class TipoPruebaRepository(private val dao: TipoPruebaDao) {

    suspend fun insertar(tipoPrueba: TipoPrueba) = dao.insertarTipoPrueba(tipoPrueba)

    suspend fun obtenerPorCurso(idCurso: Int): List<TipoPrueba> = dao.obtenerTiposPorCurso(idCurso)

    suspend fun eliminar(tipoPrueba: TipoPrueba) = dao.eliminarTipoPrueba(tipoPrueba)
}