package com.example.pcmexamen

import android.app.Application

class MailApplication : Application() {
    val database by lazy {
        BDMails.getDatabase(this)
    }
}