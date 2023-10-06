package com.example.tp3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton as FAB
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    // Déclaration des variables
    lateinit var list : MutableList<String> // Liste contenant les phrases
    lateinit var adapt : ArrayAdapter<String> // Adaptateur pour la ListView

    lateinit var phraseList : ListView // ListView pour afficher les phrases
    lateinit var newPhrase : EditText // Champ de saisie pour une nouvelle phrase
    lateinit var button: Button // Bouton pour effectuer l'action
    lateinit var add : RadioButton // Bouton radio pour ajouter une phrase
    lateinit var remove : RadioButton // Bouton radio pour supprimer une phrase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisation des variables
        list = resources.getStringArray(R.array.initphrases).toMutableList() // Récupération des phrases initiales depuis les ressources
        adapt = ArrayAdapter(this, android.R.layout.simple_list_item_1, list) // Création de l'adaptateur

        // Liaison des variables avec les éléments de l'interface
        phraseList = findViewById(R.id.listView)
        newPhrase = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        add = findViewById(R.id.ajouter)
        remove = findViewById(R.id.supprimer)

        val fab: FAB = findViewById(R.id.fab) // Bouton d'action flottant

        phraseList.adapter = adapt // Attribution de l'adaptateur à la ListView

        // Gestionnaire d'événement pour le clic sur le bouton
        button.setOnClickListener {
            action() // Appel de la fonction action()
        }

        // Gestionnaire d'événement pour le clic sur un élément de la ListView
        phraseList.setOnItemClickListener { adapterView, view, i, l ->
            newPhrase.setText((view as TextView).text.toString()) // Affichage de la phrase sélectionnée dans le champ de saisie
        }

        // Gestionnaire d'événement pour le clic sur le bouton d'action flottant
        fab.setOnClickListener {
            list.sort() // Tri de la liste des phrases
            adapt.notifyDataSetChanged() // Mise à jour de l'adaptateur
        }
    }

    // Méthode appelée lors de la sauvegarde de l'état de l'activité
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("phrases",ArrayList<String>(list)) // Sauvegarde de la liste des phrases dans le bundle
    }

    // Méthode effectuant l'action en fonction des choix de l'utilisateur
    fun action() {
        when {
            newPhrase.text.isEmpty() -> Toast.makeText(this,"Le champ est vide", Toast.LENGTH_LONG).show() // Affichage d'un message si le champ est vide
            list.contains(newPhrase.text.toString()) && add.isChecked -> Toast.makeText(this,"Cette phrase existe déjà",Toast.LENGTH_LONG).show() // Affichage d'un message si la phrase existe déjà et que l'utilisateur veut l'ajouter
            !list.contains(newPhrase.text.toString()) && remove.isChecked -> Toast.makeText(this,"Cette phrase n'existe pas",Toast.LENGTH_LONG).show() // Affichage d'un message si la phrase n'existe pas et que l'utilisateur veut la supprimer
            add.isChecked -> {
                adapt.add(newPhrase.text.toString()) // Ajout de la nouvelle phrase à la liste
                newPhrase.text.clear() // Effacement du champ de saisie
            }
            remove.isChecked -> {
                Snackbar.make(phraseList,"Confirmer la suppression",Snackbar.LENGTH_LONG).setAction("Supprimer") {
                        view ->
                    adapt.remove(newPhrase.text.toString()) // Suppression de la phrase de la liste
                    newPhrase.text.clear() // Effacement du champ de saisie
                }.show()
            }
        }
        adapt.notifyDataSetChanged() // Mise à jour de l'adaptateur
    }
}
