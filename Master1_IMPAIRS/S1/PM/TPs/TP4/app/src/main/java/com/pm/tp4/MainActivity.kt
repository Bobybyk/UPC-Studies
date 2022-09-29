package com.pm.tp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var list : MutableList<String>
    lateinit var adapt : ArrayAdapter<String>

    lateinit var buttonSave : Button
    lateinit var buttonDelete : Button
    lateinit var buttonAdd : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listPrenom = resources.getStringArray(R.array.prenom).toMutableList()
        val listNom = resources.getStringArray(R.array.nom).toMutableList()

        val listEtudiant : MutableList<Etudiant> = arrayListOf()

        for (i in 0..listPrenom.size) {
            listEtudiant.add(Etudiant(listPrenom[i], listNom[i]))
        }

        for (i in listEtudiant) {
            println(i.nom)
        }
    }
}
