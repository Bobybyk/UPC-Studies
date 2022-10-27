package fr.irif.zielonka.myapplication

import android.app.Application

class BookApplication : Application() {

    val database by lazy{
        BooksDatabase.getDatabase(this)
    }

}