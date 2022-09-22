package com.pm.tp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val edit : EditText = findViewById(R.id.editNom)
        val bMath: Button = findViewById(R.id.buttonMath)
        bMath.setOnClickListener{
            val value = edit.text.ifEmpty { "l'inconnu là" }
            Toast.makeText(this, "Test Maths pour : $value", Toast.LENGTH_SHORT).show()
            val quiz = Intent(this, MultiplyActivity::class.java)
            startActivity(quiz)
        }
    }

}