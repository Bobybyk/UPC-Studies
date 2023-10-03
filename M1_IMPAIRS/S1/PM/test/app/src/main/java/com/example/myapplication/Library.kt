package com.example.myapplication

import android.content.Context
import androidx.room.*

class GameItem (
    val name : String,
    val editor : String,
    val platform : String,
    val annotation : String
)

@Entity(indices = [Index(value = [ "name" ], unique = true)])
data class Game(
    @PrimaryKey(autoGenerate=true)
    val id: Long = 0,
    val name: String,
    val editor: String,
    val platform: String,
    val annotation: String
)

@Database(entities=[Game::class], version=1)
abstract class Library : RoomDatabase() {
    abstract fun getDao() : AnnuaireDao
    //pour avoir un singleton
    companion object {
        @Volatile
        private var instance: Library? = null

        fun getDatabase(context: Context): Library {
            if (instance != null)
                return instance!!
            val db =
                Room.databaseBuilder(context.applicationContext, Library::class.java, "games")
                    .fallbackToDestructiveMigration() /* bd détruite si on change la version */
                    .build()
            instance = db
            return instance!!
        }
    }
}