package esan.mendoza.teststudyoso.data.repository


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import esan.mendoza.teststudyoso.data.dao.CursoDao
import esan.mendoza.teststudyoso.data.entity.Curso
import kotlinx.coroutines.flow.flow

class CursoRepository(
    context: Context,
    private val cursoDao: CursoDao
) {
    suspend fun insert(curso: Curso) {
        if (curso.nombreCurso.isBlank()) {
            throw IllegalArgumentException("El nombre del curso es obligatorio")
        }
        cursoDao.insert(curso)
    }

    suspend fun update(curso: Curso) {
        cursoDao.update(curso)
    }

    suspend fun delete(curso: Curso) {
        cursoDao.delete(curso)
    }

    fun getCursosByUsuario(idUsuario: Int): LiveData<List<Curso>> {
        return flow { emit(cursoDao.getCursosByUsuario(idUsuario)) }.asLiveData()
    }

    suspend fun getCursoById(id: Int): Curso? {
        return cursoDao.getCursoById(id)
    }
}