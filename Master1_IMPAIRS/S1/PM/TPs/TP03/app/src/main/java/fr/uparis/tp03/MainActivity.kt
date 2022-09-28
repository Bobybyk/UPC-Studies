package fr.uparis.tp03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton as FAB
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var list : MutableList<String>
    lateinit var adapt : ArrayAdapter<String>

    lateinit var phraseList : ListView
    lateinit var newPhrase : EditText
    lateinit var button : Button
    lateinit var add : RadioButton
    lateinit var remove : RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = resources.getStringArray(R.array.initphrases).toMutableList()
        adapt = ArrayAdapter(this,android.R.layout.simple_list_item_1,list)

        phraseList = findViewById(R.id.listView)
        newPhrase = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        add = findViewById(R.id.ajouter)
        remove = findViewById(R.id.supprimer)
        val fab : FAB = findViewById(R.id.fab)

        phraseList.adapter = adapt
        button.setOnClickListener {
            action()
        }

        phraseList.setOnItemClickListener { adapterView, view, i, l ->
            newPhrase.setText((view as TextView).text.toString())
        }

        fab.setOnClickListener{
            list.sort()
            adapt.notifyDataSetChanged()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("phrases",ArrayList<String>(list))
    }

    fun action() {
        when {
            newPhrase.text.isEmpty() -> Toast.makeText(this,"Le champ est vide", Toast.LENGTH_LONG).show()
            list.contains(newPhrase.text.toString()) && add.isChecked -> Toast.makeText(this,"Cette phrase existe déjà",Toast.LENGTH_LONG).show()
            !list.contains(newPhrase.text.toString()) && remove.isChecked -> Toast.makeText(this,"Cette phrase n'existe pas",Toast.LENGTH_LONG).show()
            add.isChecked -> {
                adapt.add(newPhrase.text.toString())
                newPhrase.text.clear()
            }
            remove.isChecked -> {
                Snackbar.make(phraseList,"Confirmer la suppression",Snackbar.LENGTH_LONG).setAction("Supprimer") {
                    view ->
                    adapt.remove(newPhrase.text.toString())
                    newPhrase.text.clear()
                }.show()
            }
        }
        adapt.notifyDataSetChanged()
    }
}