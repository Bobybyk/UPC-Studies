package fr.irif.zielonka.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SearchAuthorViewModel(application : Application) : AndroidViewModel(application){
    private val dao = (application as BookApplication).database.myDao()

    val allAuthors :  LiveData<List<Author>> = dao.loadAllAuthors()

    var selectedAuthors = MutableLiveData( mutableListOf<Author>() )

    val checkedAuthors = mutableListOf<Author>()

    val authors = MutableLiveData<List<Author>>(  )

    fun loadSomeAuthors( prefix : String ){
        if( prefix.isEmpty() ){
            authors.value = allAuthors.value
            return
        }
        Thread {
            val l = dao.loadSomeAuthors(prefix)
            authors.postValue(l)
        }.start()
    }
}
