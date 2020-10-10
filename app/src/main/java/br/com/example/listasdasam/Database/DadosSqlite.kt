package br.com.example.example.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.example.listasdasam.Database.listaDao
import br.com.example.listasdasam.Modelo.Lista

@Database(entities = [Lista::class], version = 3)
abstract class DadosSqlite : RoomDatabase() {

    abstract fun listaDao() : listaDao

    companion object {

        @Volatile
        private var INSTANCE: DadosSqlite? = null

        fun getDatabase(context: Application) : DadosSqlite {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DadosSqlite::class.java,
                    "Listas")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
//                    .createFromAsset("Listas.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }

    }
}