package fr.uparis.exam

import android.app.Application
import fr.irif.zielonka.examjanviera.AnnuaireBD

/*
 * Cette ligne crée une propriété database qui est une instance de la base de données AnnuaireBD.
 * La propriété est définie en utilisant la délégation by lazy, ce qui signifie que
 * l'initialisation de la propriété est retardée jusqu'à ce qu'elle soit réellement utilisée.
 * L'initialisation de la base de données est effectuée en appelant la méthode statique
 * getDatabase() de la classe AnnuaireBD en passant this comme contexte.
 * Cette classe AnnuaireApplication peut être utilisée dans l'application Android comme
 * point d'accès à l'instance de la base de données AnnuaireBD.
 */
class AnnuaireApplication: Application() {
    val database by lazy {
        AnnuaireBD.getDatabase(this)
    }
}