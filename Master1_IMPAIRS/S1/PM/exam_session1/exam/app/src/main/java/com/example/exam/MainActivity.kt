package com.example.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam.adapter.DestinataireAdapter
import com.example.exam.data.BDMails
import com.example.exam.data.DestinataireItem

class MainActivity : AppCompatActivity() {

    lateinit var nom : EditText
    lateinit var prenom : EditText
    lateinit var mail : EditText
    lateinit var annotation: EditText

    lateinit var afficher_annotation : Button
    lateinit var ajouter_destinataire : Button
    lateinit var envoyer_mail : Button

    // Déclarez les variables pour le RecyclerView et son Adapter
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: DestinataireAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisez le RecyclerView et son Adapter
        recyclerView = findViewById(R.id.recycler)
        adapter = DestinataireAdapter()

        // Définissez le LinearLayoutManager pour le RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        nom = findViewById(R.id.nom)
        prenom = findViewById(R.id.prenom)
        mail = findViewById(R.id.mail)
        annotation = findViewById(R.id.annotation)

        afficher_annotation = findViewById(R.id.afficherAnnotationButton)
        ajouter_destinataire = findViewById(R.id.ajouterDestinataireButton)
        envoyer_mail = findViewById(R.id.envoyerMailButton)

        ajouter_destinataire.setOnClickListener {
            val nomDestinataire = nom.text.toString()
            val prenomDestinataire = prenom.text.toString()
            val mailDestinataire = mail.text.toString()
            val annotationDestinataire = annotation.text.toString()

            val dao = BDMails.getDatabase(applicationContext).monDao()

            Thread {
                dao.insertDestinataire(DestinataireItem(
                    nomDestinataire,
                    prenomDestinataire,
                    mailDestinataire,
                    annotationDestinataire)
                )
            }.start()

            // Observez les changements dans la liste des destinataires
            dao.getAllDestinataires().observe(this, Observer { destinataires ->
                adapter.setDestinataires(destinataires)
            })
        }
    }
}