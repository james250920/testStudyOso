package esan.mendoza.teststudyoso.data.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import esan.mendoza.teststudyoso.data.entity.Calificacion
import esan.mendoza.teststudyoso.data.entity.Curso
import esan.mendoza.teststudyoso.data.entity.Horario
import esan.mendoza.teststudyoso.data.entity.Tarea
import esan.mendoza.teststudyoso.data.entity.TipoPrueba
import esan.mendoza.teststudyoso.data.entity.Usuario
import esan.mendoza.teststudyoso.data.dao.CalificacionDao
import esan.mendoza.teststudyoso.data.dao.CursoDao
import esan.mendoza.teststudyoso.data.dao.HorarioDao
import esan.mendoza.teststudyoso.data.dao.TareaDao
import esan.mendoza.teststudyoso.data.dao.TipoPruebaDao
import esan.mendoza.teststudyoso.data.dao.UsuarioDao

@Database(
    entities = [
        Usuario::class,
        Curso::class,
        TipoPrueba::class,
        Tarea::class,
        Horario::class,
        Calificacion::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "app_database"
    }
    abstract fun usuarioDao(): UsuarioDao
    abstract fun cursoDao(): CursoDao
    abstract fun tipoPruebaDao(): TipoPruebaDao
    abstract fun tareaDao(): TareaDao
    abstract fun horarioDao(): HorarioDao
    abstract fun calificacionDao(): CalificacionDao
}




