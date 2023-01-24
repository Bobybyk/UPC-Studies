package com.example.test_exam

import android.content.Context
import androidx.room.*


class AnnuaireItem(
    val nom: String,        //nom de la personne
    val prenom : String,    //prénom de la personne
    val numero : String,    //numéro de téléphone
    val annotation: String  //les annotations quelconques concernant la personne
)

@Entity( indices = [Index( value = [ "nom", "prenom"], unique=true)] )
data class Annuaire(
    @PrimaryKey(autoGenerate=true)
    val id: Long =0,
    val nom: String,
    val prenom : String,
    val numero : String,
    val annotation : String
)
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