package com.example.exam.data

import android.content.Context
import androidx.room.*
import com.example.exam.data.MonDao


@Entity
data class Destinataire(
    @PrimaryKey(autoGenerate = true)
    val idDest: Long = 0,
    var nom: String,  /* nom de destinataire */
    var prenom: String, /* prénom de destinataire */
    var mail: String, /* l'adresse mail de destinataire */
    var annotation : String  /* une annotation */
)

@Database(entities=[Destinataire::class ], version = 1)
abstract class BDMails : RoomDatabase() {
    abstract fun monDao(): MonDao

    companion object {
        @Volatile
        private var instance: BDMails? = null

        fun getDatabase( context : Context): BDMails{
            if( instance != null )
                return instance!!
            val db = Room.databaseBuilder( context.applicationContext,
                BDMails::class.java ,
                "annuaire")
                .fallbackToDestructiveMigration() /* bd détruite si on change la version */
                .build()
            instance = db
            return instance!!
        }
    }
}