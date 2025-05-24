package esan.mendoza.teststudyoso.data.repository


import esan.mendoza.teststudyoso.data.dao.TareaDao
import esan.mendoza.teststudyoso.data.entity.Tarea



class TareaRepository(private val dao: TareaDao) {

    suspend fun insertar(tarea: Tarea) = dao.insertarTarea(tarea)

    suspend fun obtenerPorUsuario(idUsuario: Int): List<Tarea> = dao.obtenerTareasPorUsuario(idUsuario)

    suspend fun eliminar(tarea: Tarea) = dao.eliminarTarea(tarea)
}