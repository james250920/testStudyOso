package esan.mendoza.teststudyoso

import android.app.Application
import androidx.room.Room
import esan.mendoza.teststudyoso.data.db.AppDatabase

class MainApplication : Application() {
    companion object {
        lateinit var appDatabase: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.getDatabase(context = this).javaClass.name
        ).fallbackToDestructiveMigration(true)
            .build()
    }
}