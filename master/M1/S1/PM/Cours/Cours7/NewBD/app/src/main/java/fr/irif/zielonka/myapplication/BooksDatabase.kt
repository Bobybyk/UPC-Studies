package fr.irif.zielonka.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Author::class, Book::class, AuthorBook::class], version = 7)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao

    //pour avoir un singleton
    companion object {
        @Volatile
        private var instance: BooksDatabase? = null

        fun getDatabase(context: Context): BooksDatabase {
            if (instance != null)
                return instance!!
            val db =
                Room.databaseBuilder(context.applicationContext, BooksDatabase::class.java, "books")
                    .fallbackToDestructiveMigration() /* bd détruite si on change la version */
                    .build()
            instance = db
            return instance!!
        }
    }
}
