package com.example.tp7.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pays(
    @PrimaryKey(autoGenerate = false) val pays: String,
    val continent: String,
    val capitale: String,
    val superficie: Int?
)