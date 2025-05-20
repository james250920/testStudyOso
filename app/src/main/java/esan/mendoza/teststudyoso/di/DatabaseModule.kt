package esan.mendoza.teststudyoso.di




import esan.mendoza.teststudyoso.data.db.AppDatabase
import esan.mendoza.teststudyoso.data.dao.CalificacionDao
import esan.mendoza.teststudyoso.data.dao.CursoDao
import esan.mendoza.teststudyoso.data.dao.HorarioDao
import esan.mendoza.teststudyoso.data.dao.TareaDao
import esan.mendoza.teststudyoso.data.dao.TipoPruebaDao
import esan.mendoza.teststudyoso.data.dao.UsuarioDao
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.getDatabase(context).javaClass.name
        ).build()
    }

    @Provides
    fun provideUsuarioDao(database: AppDatabase): UsuarioDao {
        return database.usuarioDao()
    }

    @Provides
    fun provideCursoDao(database: AppDatabase): CursoDao {
        return database.cursoDao()
    }

    @Provides
    fun provideHorarioDao(database: AppDatabase): HorarioDao {
        return database.horarioDao()
    }

    @Provides
    fun provideTareaDao(database: AppDatabase): TareaDao {
        return database.tareaDao()
    }

    @Provides
    fun provideTipoPruebaDao(database: AppDatabase): TipoPruebaDao {
        return database.tipoPruebaDao()
    }

    @Provides
    fun provideCalificacionDao(database: AppDatabase): CalificacionDao {
        return database.calificacionDao()
    }
}
