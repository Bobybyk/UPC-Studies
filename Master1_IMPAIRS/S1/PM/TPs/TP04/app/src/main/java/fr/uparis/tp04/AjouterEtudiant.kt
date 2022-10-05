package fr.uparis.tp04

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AjouterEtudiant : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajouter_etudiant)

        val prenom : EditText = findViewById(R.id.prenom)
        val nom : EditText = findViewById(R.id.nom)
        val submit : Button = findViewById(R.id.ajouter)

        submit.setOnClickListener {
            if(!prenom.text.isEmpty() && !nom.text.isEmpty()) {
                val result = Intent(this,MainActivity::class.java)
                result.putExtra("prenom",prenom.text.toString())
                result.putExtra("nom",nom.text.toString())
                setResult(RESULT_OK,result)
                finish()
            } else {
                Toast.makeText(this,"Veuillez remplir tous les champs",Toast.LENGTH_SHORT).show()
            }
        }
    }
}