package esan.mendoza.teststudyoso.data.repository



import esan.mendoza.teststudyoso.data.dao.CalificacionDao
import esan.mendoza.teststudyoso.data.entity.Calificacion


class CalificacionRepository(private val dao: CalificacionDao) {

    suspend fun insertar(calificacion: Calificacion) = dao.insertarCalificacion(calificacion)

    suspend fun obtenerPorCursoYTipo(idCurso: Int, idTipoPrueba: Int): List<Calificacion> =
        dao.obtenerCalificacionesPorCursoYTipo(idCurso, idTipoPrueba)

    suspend fun eliminar(calificacion: Calificacion) = dao.eliminarCalificacion(calificacion)
}