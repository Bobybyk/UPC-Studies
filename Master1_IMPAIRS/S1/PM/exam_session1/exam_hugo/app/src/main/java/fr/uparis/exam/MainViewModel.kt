package fr.uparis.exam

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import fr.irif.zielonka.examjanviera.AnnuaireBD

class MainViewModel(application: Application) : AndroidViewModel(application) {

    /* création d'une instance d'un objet DAO (Data Acces Object)
     * en utilisant la classe AnnuaireBD. AnnuaireBD est utilisé pour
     * obtenir une instance de base de données en appelant getDatabase() et
     * ensuite obtenir le DAO spécifique en appelant getDao().
     * L'instance de base de données est obtenue en utilisant le
     * contexte d'application passé en paramètre.
     */
    private val dao = AnnuaireBD.getDatabase(application.applicationContext).getDao()

    /*
     * Cette fonction loadItems() utilise le DAO pour obtenir tous les éléments de
     * la base de données. Elle retourne directement le résultat de l'appel à allItems().
     * Cela signifie que cette fonction renvoie une liste d'éléments provenant de la base de données.
     * Ce ViewModel est responsable de la récupération des données à partir de la base de données
     * en utilisant le DAO, et il expose la fonction loadItems() pour obtenir ces données.
     * Ce ViewModel peut être utilisé par une activité ou un fragment pour obtenir les données
     * nécessaires à l'affichage et à la manipulation des éléments de la base de données.
     */
    fun loadItems() = dao.allItems()

}