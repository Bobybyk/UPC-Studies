package com.example.tp7.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PaysDao {

    @Insert(entity = Pays::class, onConflict = OnConflictStrategy.ABORT)
    fun addPays(vararg pays: Pays): List<Long>

    @Query("SELECT * FROM Pays")
    fun loadAll(): LiveData<List<Pays>>

    @Query("SELECT * FROM Pays WHERE pays like :pays || '%' ")
    fun loadPartialName(pays: String): List<Pays>

}
// 2 Les activités