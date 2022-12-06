package com.example.tp7.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Update
import com.example.tp7.GeographyApplication
import com.example.tp7.data.Pays
import kotlin.concurrent.thread

class AjoutViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = (application as GeographyApplication).database.paysDao()

    var resultPartialName : LiveData<List<Pays>>? = null
    fun loadPartialName(pays: String): LiveData<List<Pays>>? {
        resultPartialName = dao.loadPartialName(pays)
        return resultPartialName
    }

    fun loadAll() = dao.loadAll()

    val deleteInfo = MutableLiveData<Int>(0)
    fun deletePays(vararg pays: Pays) {
        thread {
            val i = dao.deletePays(*pays)
            deleteInfo.postValue(i)
        }
    }

    val insertInfo = MutableLiveData<Int>(0)
    fun addPays(vararg pays: Pays) {
        //Rq: thread{} <=> Thread{}.start()
        thread {
            val l = dao.addPays(*pays)
            insertInfo.postValue(l.fold(0)
            { acc: Int, n: Long -> if (n >= 0) acc + 1 else acc })
            Log.e("AjoutViewModel", "insert ${insertInfo.value} elements")
        }
    }

    val updateInfo = MutableLiveData<Int>(0)
    fun updatePays(pays: Pays){
        thread {
            val i = dao.updatePays(pays)
            updateInfo.postValue(i)
            Log.e("updatePays", "insert ${insertInfo.value} elements")
        }
    }

}