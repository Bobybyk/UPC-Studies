package com.example.tp7

import android.app.Application
import com.example.tp7.data.GeoBD

class GeographyApplication : Application() {

    val database by lazy {
        GeoBD.getDatabase(this)
    }

}