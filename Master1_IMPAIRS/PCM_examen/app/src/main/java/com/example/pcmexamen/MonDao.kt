package com.example.pcmexamen

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

data class DestinataireItem(
    var nom: String,  /* nom de destinataire */
    var prenom: String, /* prénom de destinataire */
    var mail: String, /* l'adresse mail de destinataire */
    var annotation : String  /* une annotation */
)

@Dao
interface MonDao {

    @Insert(entity = Destinataire::class)

    fun insertDestinataire(destinataire : DestinataireItem) : Long

    @Query("SELECT * FROM Destinataire")
    fun getAllDestinataires() : LiveData<List<Destinataire>>

    @Query("SELECT * FROM Destinataire WHERE idDest = :idDest ")
    fun getDestinataireById( idDest: Long ) : LiveData<Destinataire>

}