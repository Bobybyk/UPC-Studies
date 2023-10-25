package fr.uparis.exam

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import fr.irif.zielonka.examjanviera.AnnuaireBD

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AnnuaireBD.getDatabase(application.applicationContext).getDao()

    fun loadItems() = dao.allItems()

}