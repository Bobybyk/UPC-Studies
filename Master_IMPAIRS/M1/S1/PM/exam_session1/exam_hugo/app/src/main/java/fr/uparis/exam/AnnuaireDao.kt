package fr.irif.zielonka.examjanviera

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/*
 * Cette ligne déclare l'interface AnnuaireDao et l'annotant avec @Dao,
 * ce qui indique à Room que cette interface représente un Data Access Object.
 */
@Dao
interface AnnuaireDao {

    /*
     * Cette fonction insertItem est une opération d'insertion dans la base de données.
     * Elle insère un objet AnnuaireItem dans la table de la base de données associée à
     * l'entité Annuaire. L'annotation @Insert spécifie l'entité cible, tandis que
     * onConflict = OnConflictStrategy.ABORT indique la stratégie de résolution de conflit
     * en cas de conflit lors de l'insertion.
     */
    @Insert(entity = Annuaire::class, onConflict = OnConflictStrategy.ABORT)
    fun insertItem(item: AnnuaireItem): Long

    /* Cette fonction allItems est une requête de sélection qui récupère tous les éléments de
     * la table Annuaire de la base de données. Elle renvoie un objet LiveData qui contient une
     * liste d'objets Annuaire. LiveData est utilisé pour permettre la mise à jour automatique
     * des données lorsque la base de données est modifiée.
     */
    @Query("SELECT * FROM Annuaire")
    fun allItems(): LiveData<List<Annuaire>>

    /*
     * Cette fonction itemById est une requête de sélection qui récupère un élément de la table
     * Annuaire de la base de données en fonction de son id. Elle renvoie un objet LiveData qui
     * contient l'objet Annuaire correspondant à l'id spécifié.
     */
    @Query("SELECT * FROM Annuaire WHERE  id = :id ")
    fun itemById(id: Long) : LiveData<Annuaire>

    /*
     * Cette fonction updateItem est une opération de mise à jour dans la base de données.
     * Elle met à jour un objet Annuaire existant dans la table de la base de données.
     * L'annotation @Update spécifie que la fonction est une opération de mise à jour.
     * Ces opérations définies dans l'interface AnnuaireDao sont utilisées pour interagir avec
     * la base de données en effectuant des opérations de création, de lecture et de mise à jour
     * sur la table Annuaire.
     */
    @Update
    fun updateItem( item : Annuaire )
}