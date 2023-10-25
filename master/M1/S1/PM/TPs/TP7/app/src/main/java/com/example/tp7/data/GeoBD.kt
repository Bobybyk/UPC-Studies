package com.example.tp7.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pays::class], version = 1)
abstract class GeoBD : RoomDatabase() {

    abstract fun paysDao(): PaysDao

    companion object {
        @Volatile
        private var INSTANCE: GeoBD? = null

        fun getDatabase(context: Context): GeoBD {
            if (INSTANCE != null)
                return INSTANCE!!

            val db = Room.databaseBuilder(
                context.applicationContext,
                GeoBD::class.java, "books"
            )
                .fallbackToDestructiveMigration()
                .build()

            INSTANCE = db
            return INSTANCE!!
        }
    }

}