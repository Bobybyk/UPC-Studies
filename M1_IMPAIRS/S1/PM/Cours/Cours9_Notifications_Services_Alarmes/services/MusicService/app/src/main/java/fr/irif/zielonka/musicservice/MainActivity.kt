package fr.irif.zielonka.musicservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View




class MainActivity : AppCompatActivity() {

    val id by lazy{ R.raw.sample }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun start(view: View) {
        val serviceIntent = Intent(this, MusicService::class.java).apply {
            action = MusicService.START_ACTION
            data = getUriFromRes(this@MainActivity, id)
        }
        startService(serviceIntent)
    }

    fun stop(view: View){
        val serviceIntent = Intent(this, MusicService::class.java)
        stopService(serviceIntent)
    }

    fun pause(  view: View){
        Log.d("main activity", "pause")
        val serviceIntent = Intent(this, MusicService::class.java).apply {
            action = MusicService.PAUSE_ACTION
        }
        startService(serviceIntent)
    }
}