package com.example.tp7.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.tp7.GeographyApplication
import com.example.tp7.data.Pays
import kotlin.concurrent.thread

class AjoutViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = (application as GeographyApplication).database.paysDao()

    val adptr: MutableLiveData<List<Pays>> = MutableLiveData()
    fun loadPartialName(pays: String) {
        thread {
            adptr.postValue(
                dao.loadPartialName(pays.toString()).toMutableList()
            )
        }
    }

    fun loadAll() = dao.loadAll()

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

}