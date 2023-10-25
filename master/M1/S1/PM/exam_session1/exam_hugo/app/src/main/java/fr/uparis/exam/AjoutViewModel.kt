package fr.uparis.exam

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import fr.irif.zielonka.examjanviera.AnnuaireBD
import fr.irif.zielonka.examjanviera.AnnuaireItem
import kotlin.concurrent.thread

class AjoutViewModel(application: Application) : AndroidViewModel(application){

    /*
     * Cette ligne crée une variable dao qui représente l'objet AnnuaireDao associé à
     * la base de données AnnuaireBD. La base de données est obtenue en appelant la
     * méthode statique getDatabase() de AnnuaireBD en passant le contexte de l'application.
     */
    private val dao = AnnuaireBD.getDatabase(application.applicationContext).getDao()

    /*
     * Cette ligne crée une variable insertInfo qui est une instance de MutableLiveData<Int>.
     * Elle est utilisée pour signaler le résultat de l'insertion d'un élément dans
     * la base de données. La valeur initiale est définie sur 0.
     */
    var insertInfo = MutableLiveData(0)

    /*
     * Cette fonction insertItem est appelée pour insérer un nouvel élément dans la base de données.
     * Elle s'exécute dans un thread distinct en utilisant la fonction thread de Kotlin.
     * À l'intérieur du thread, la méthode insertItem de l'objet AnnuaireDao est appelée pour
     * effectuer l'insertion. La valeur de retour est stockée dans la variable l, puis
     * elle est convertie en Int et postée sur insertInfo à l'aide de postValue() de MutableLiveData.

En résumé, ce ViewModel est responsable de la gestion des opérations d'insertion d'éléments dans la base de données AnnuaireBD. Il expose insertInfo comme un MutableLiveData pour informer les observateurs du résultat de l'insertion.
     */
    fun insertItem(item : AnnuaireItem) {
        thread {
            val l = dao.insertItem(item)
            insertInfo.postValue(l.toInt())
        }
    }

}