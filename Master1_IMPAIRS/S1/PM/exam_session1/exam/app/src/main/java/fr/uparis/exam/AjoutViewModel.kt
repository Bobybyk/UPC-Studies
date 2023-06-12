package fr.uparis.exam

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import fr.irif.zielonka.examjanviera.AnnuaireBD
import fr.irif.zielonka.examjanviera.AnnuaireItem
import kotlin.concurrent.thread

class AjoutViewModel(application: Application) : AndroidViewModel(application){

    private val dao = AnnuaireBD.getDatabase(application.applicationContext).getDao()

    var insertInfo = MutableLiveData(0)
    fun insertItem(item : AnnuaireItem) {
        thread {
            val l = dao.insertItem(item)
            insertInfo.postValue(l.toInt())
        }
    }

}