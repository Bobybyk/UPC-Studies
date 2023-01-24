package com.example.pcmexamen.databinding

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pcmexamen.Destinataire
import com.example.pcmexamen.MailApplication
import kotlin.concurrent.thread

class ManageMailViewModel (application: Application) : AndroidViewModel(application) {
    private val dao = (application as MailApplication).database.monDao()

    var loadInfo = MutableLiveData<List<Destinataire>>(emptyList())
    fun loadAllDestinataires() {
        thread {
            loadInfo.postValue(dao.getAllDestinataires().value)
        }
    }

    var insert = MutableLiveData(0)
    fun insertDestinataire(vararg dest: Destinataire) {
        thread {
            /* val list = dao.insertDestinataire(*dest)
            insert.postValue(list.fold(0) {acc: Int, l: Long ->
                if(l >= 0) acc + 1 else acc
            }) */
        }
    }

}