package esan.mendoza.teststudyoso.data.repository


import esan.mendoza.teststudyoso.data.dao.CursoDao
import esan.mendoza.teststudyoso.data.entity.Curso


class CursoRepository(private val dao: CursoDao) {

    suspend fun insertarCurso(curso: Curso) = dao.insertarCurso(curso)

    suspend fun obtenerCursosPorUsuario(idUsuario: Int): List<Curso> =
        dao.obtenerCursosPorUsuario(idUsuario)

    suspend fun eliminarCurso(curso: Curso) = dao.eliminarCurso(curso)
}