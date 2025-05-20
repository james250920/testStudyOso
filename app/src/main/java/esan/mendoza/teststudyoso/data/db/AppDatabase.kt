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
    entities = [Usuario::class, Curso::class, Horario::class, Tarea::class, TipoPrueba::class, Calificacion::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun cursoDao(): CursoDao
    abstract fun horarioDao(): HorarioDao
    abstract fun tareaDao(): TareaDao
    abstract fun tipoPruebaDao(): TipoPruebaDao
    abstract fun calificacionDao(): CalificacionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "studyOso_db"
                )
                    .allowMainThreadQueries() // For simplicity; use coroutines in production
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}