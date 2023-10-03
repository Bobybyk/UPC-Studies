package com.example.tp7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.tp7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.enter_data).setOnClickListener {
            startActivity(Intent(this@MainActivity, AjoutPaysActivity::class.java))
        }

        findViewById<Button>(R.id.consulter_data).setOnClickListener {
            startActivity(Intent(this@MainActivity, RechercheActivity::class.java))
        }

    }

}