package com.example.test_exam

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnnuaireDao {

    @Insert(entity = Annuaire::class, onConflict = OnConflictStrategy.ABORT)
    fun insertItem(item: AnnuaireItem): Long

    @Query("SELECT * FROM Annuaire")
    fun allItems(): LiveData<List<Annuaire>>

    @Query("SELECT * FROM Annuaire WHERE  id = :id ")
    fun itemById(id: Long) : LiveData<Annuaire>

    @Update
    fun updateItem( item : Annuaire )
}