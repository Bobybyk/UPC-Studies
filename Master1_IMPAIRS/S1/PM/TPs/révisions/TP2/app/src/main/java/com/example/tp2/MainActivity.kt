package com.example.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val edit : EditText = findViewById(R.id.editNom);
        val bMath: Button = findViewById(R.id.buttonMath);
        val bFrançais: Button = findViewById(R.id.buttonFr);
        bMath.setOnClickListener{
            val value = edit.text.ifEmpty { "Unk user" }
            Toast.makeText(this, "Test Maths pour : $value", Toast.LENGTH_SHORT).show()
        }
        bFrançais.setOnClickListener{
            Toast.makeText(this, "pas encore dispo", Toast.LENGTH_SHORT).show()
        }
    }
}