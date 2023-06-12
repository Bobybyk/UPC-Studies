package fr.irif.zielonka.examjanviera

import android.content.Context
import androidx.room.*


class AnnuaireItem(
    val nom: String,        //nom de la personne
    val prenom : String,    //prénom de la personne
    val numero : String,    //numéro de téléphone
    val annotation: String  //les annotations quelconques concernant la personne
)

/*
 * Cette classe Annuaire est une entité Room qui représente une entrée dans la table de
 * la base de données. Chaque instance de Annuaire correspond à une ligne dans la table.
 * Les annotations @Entity et @PrimaryKey indiquent à Room de traiter cette classe comme
 * une entité et de définir la clé primaire de la table. L'annotation @Entity spécifie également
 * un indice unique sur les colonnes "nom" et "prenom" pour garantir l'unicité des enregistrements.
 */
@Entity( indices = [Index( value = [ "nom", "prenom"], unique=true)] )
data class Annuaire(
    @PrimaryKey(autoGenerate=true)
    val id: Long =0,
    val nom: String,
    val prenom : String,
    val numero : String,
    val annotation : String
)

/*
 * Cette classe AnnuaireBD est une sous-classe de RoomDatabase et représente la base de données Room.
 * Elle est annotée avec @Database pour indiquer à Room que cette classe est une base de données et
 * qu'elle contient une liste d'entités spécifiées ([Annuaire::class]).
 * La version de la base de données est également spécifiée.
 *
 * La fonction getDao() est une fonction abstraite qui renvoie l'objet AnnuaireDao
 * associé à cette base de données.
 *
 * La classe AnnuaireBD possède également un objet compagnon companion object qui permet de
 * créer un singleton pour assurer qu'une seule instance de la base de données est utilisée
 * dans l'application. La fonction getDatabase() est utilisée pour obtenir une instance de
 * la base de données. Si une instance existe déjà, elle est renvoyée, sinon une nouvelle instance
 * est créée en utilisant Room.databaseBuilder(). La méthode `fallbackToDestruct
 */
@Database(entities=[Annuaire::class], version=1)
abstract class AnnuaireBD : RoomDatabase() {
    abstract fun getDao() : AnnuaireDao
    //pour avoir un singleton
    companion object {
        @Volatile
        private var instance: AnnuaireBD? = null

        fun getDatabase(context: Context): AnnuaireBD {
            if (instance != null)
                return instance!!
            val db =
                Room.databaseBuilder(context.applicationContext, AnnuaireBD::class.java, "books")
                    .fallbackToDestructiveMigration() /* bd détruite si on change la version */
                    .build()
            instance = db
            return instance!!
        }
    }
}