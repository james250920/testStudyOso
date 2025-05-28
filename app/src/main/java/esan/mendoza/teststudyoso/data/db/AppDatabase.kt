package esan.mendoza.teststudyoso.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import esan.mendoza.teststudyoso.data.dao.CalificacionDao
import esan.mendoza.teststudyoso.data.dao.CursoDao
import esan.mendoza.teststudyoso.data.dao.HorarioDao
import esan.mendoza.teststudyoso.data.dao.TareaDao
import esan.mendoza.teststudyoso.data.dao.TipoPruebaDao
import esan.mendoza.teststudyoso.data.dao.UsuarioDao
import esan.mendoza.teststudyoso.data.entities.Calificacion
import esan.mendoza.teststudyoso.data.entities.Curso
import esan.mendoza.teststudyoso.data.entities.Horario
import esan.mendoza.teststudyoso.data.entities.Tarea
import esan.mendoza.teststudyoso.data.entities.TipoPrueba
import esan.mendoza.teststudyoso.data.entities.Usuario

@Database(
    entities = [
        Usuario::class,
        Curso::class,
        TipoPrueba::class,
        Horario::class,
        Tarea::class,
        Calificacion::class
               ], version = 1, exportSchema = true)
internal abstract class AppDatabase: RoomDatabase(){
    abstract fun UsuarioDao(): UsuarioDao
    abstract fun CursoDao(): CursoDao
    abstract fun TipoPruebaDao(): TipoPruebaDao
    abstract fun HorarioDao(): HorarioDao
    abstract fun TareaDao(): TareaDao
    abstract fun CalificacionDao(): CalificacionDao


    companion object{
        @Volatile
        private var INSTANCE : AppDatabase?=null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "TESTOSO_DB"
                ).build().also { INSTANCE = it }
            }
        }
    }
}