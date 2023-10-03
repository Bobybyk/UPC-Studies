package fr.uparis.exam

import android.app.Application
import fr.irif.zielonka.examjanviera.AnnuaireBD

class AnnuaireApplication: Application() {
    val database by lazy {
        AnnuaireBD.getDatabase(this)
    }
}