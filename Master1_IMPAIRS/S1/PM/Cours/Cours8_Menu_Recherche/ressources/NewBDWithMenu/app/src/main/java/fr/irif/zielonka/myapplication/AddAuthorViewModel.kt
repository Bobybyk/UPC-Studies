package fr.irif.zielonka.myapplication

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class AddAuthorViewModel(application: Application) : AndroidViewModel(application) {
    //val dao = BooksDatabase.getDatabase(application).myDao()
    val dao = (application as BookApplication).database.myDao()
    lateinit var sortType: String

    companion object {
        const val byName = "byName"
        const val byFirstname = "byFirstname"
        const val TAG = "AddAuthorViewModel"
    }
    //val authors : LiveData<List<Author>> = dao.loadAllAuthors()

    //val authors : MutableLiveData<List<Author>> = MutableLiveData( listOf<Author>() )
    fun loadAllAuthors() = dao.loadAllAuthors()

    fun loadAuthorsByNamePrefix(prefix: String) = dao.loadAuthorsByNamePrefix(prefix)


    /* la liste d'auteurs sélectionés */
    val selectedAuthors: MutableList<Author> = mutableListOf()
/*
    val insertInfo = MutableLiveData<Int>(-1)
    fun insertAuthor(nom: String, prenom: String) {
        Thread {
            val l = dao.insertAuthors(AuthorName(name = nom.trim(), firstName = prenom.trim()))
            insertInfo.postValue(if (l[0] == -1L) 0 else 1)
            Log.d(TAG, "insert ${insertInfo.value} elements")
        }.start()
    }
*/

  //  val insertInfo = MutableLiveData<List<Long>>()
    fun insertAuthor(nom: String, prenom: String) {
        Thread {
            val l = dao.insertAuthors(AuthorName(name = nom.trim(), firstName = prenom.trim()))
            insertInfo.postValue(if (l[0] == -1L) 0 else 1)
            Log.d(TAG, "insert ${insertInfo.value} elements")
        }.start()
    }



    val insertInfo = MutableLiveData<Int>(0)
    fun insertAuthors(vararg authors: AuthorName) {
        Thread {
            val l = dao.insertAuthors(*authors)
            insertInfo.postValue(l.fold(0) { acc: Int, n: Long -> if (n >= 0) acc + 1 else acc })
            Log.d(TAG, "insert ${insertInfo.value} elements")
        }.start()
    }

    /* supprimer les auteurs qui se trouvent sur la liste selectedAuthors */
    fun removeAuthors() {
        Thread {
            dao.deleteSomeAuthors(selectedAuthors)
            selectedAuthors.clear()
        }.start()
    }

}