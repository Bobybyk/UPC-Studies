package com.example.tp7

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tp7.data.Pays
import com.example.tp7.model.AjoutViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AjoutPaysActivity : AppCompatActivity() {

    private val model by lazy { ViewModelProvider(this)[AjoutViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajout_pays)

        findViewById<Button>(R.id.b_ajouter).setOnClickListener {

            val edPays = findViewById<EditText>(R.id.ed_pays)
            val edCapitale = findViewById<EditText>(R.id.ed_capitale)
            val edContinent = findViewById<EditText>(R.id.ed_continent)
            val edSuperficie = findViewById<EditText>(R.id.ed_superficie)

            var emptyField = ""
            var editEmpty = edCapitale

            emptyField += if (edCapitale.text.toString().trim().isEmpty()) {
                "Capitale "
            } else ""

            if (edContinent.text.toString().trim().isEmpty()) {
                editEmpty = edContinent
                emptyField += "Continent "
            }

            if (edPays.text.toString().trim().isEmpty()) {
                editEmpty = edPays
                emptyField += "Pays"
            }

            if (emptyField.isNotEmpty()) {
                MaterialAlertDialogBuilder(this)
                    .setTitle("")
                    .setMessage("Les champs suivants sont manquants: $emptyField")
                    .setPositiveButton("C'est compris") { _, _ ->
                        editEmpty.requestFocus()
                    }
                    .setCancelable(false)
                    .show()
            } else {

                try {
                    model.addPays(
                        Pays(
                            edPays.text.toString().trim(),
                            edContinent.text.toString().trim(),
                            edCapitale.text.toString().trim(),
                            if (edSuperficie.text.toString().trim().isEmpty()) {
                                null
                            } else {
                                edSuperficie.text.toString().trim().toInt()
                            }
                        )
                    )
                } catch (e: Exception) {
                    Toast.makeText(this, "impossible d'enregistrer ce pays", Toast.LENGTH_LONG)
                        .show()
                } finally {
                    model.insertInfo.observe(this) {
                        Toast.makeText(this, "inserer $it élément(s)", Toast.LENGTH_LONG).show()
                    }
                }
                model.loadAll().observe(this) {
                    it.forEach { it ->
                        Log.e(it.pays, "List pays add")
                    }
                }

                edPays.text.clear()
                edCapitale.text.clear()
                edContinent.text.clear()
                edSuperficie.text.clear()

                edPays.requestFocus()

            }

        }


    }

}