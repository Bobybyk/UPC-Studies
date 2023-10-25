package com.example.tp4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AjouterEtudiant: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajouter_etudiant)

        addUser()

    }

    fun addUser(){
        findViewById<Button>(R.id.ajouter).setOnClickListener {
            val nom: String = findViewById<EditText>(R.id.nom).text.toString()
            val prenom: String = findViewById<EditText>(R.id.prenom).text.toString()
            if (nom.isNotEmpty() && prenom.isNotEmpty()){
                val iii = Intent(this@AjouterEtudiant, MainActivity::class.java)
                iii.putExtra("Etudiant_prenom", prenom)
                iii.putExtra("Etudiant_nom", nom)
                setResult(RESULT_OK, iii)
                finish()
            } else {
                Toast.makeText(this@AjouterEtudiant, "Les champs sont incorrects.", Toast.LENGTH_LONG).show()
            }
        }
    }

}