package fr.uparis.tp04

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var studentList = mutableListOf<Etudiant>()
    lateinit var adapter : MonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buildStudents()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        val colorEven: Int = resources.getColor(R.color.couleurPaire, null)
        val colorOdd: Int = resources.getColor(R.color.couleurImpaire, null)
        val colorChecked: Int = resources.getColor(R.color.couleurChecked, null)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MonAdapter(studentList, colorEven, colorOdd, colorChecked)
        recyclerView.adapter = adapter

        val delete: Button = findViewById(R.id.supprimer)
        delete.setOnClickListener {
            var indexes = adapter.getSelected()
            indexes.forEach {
                studentList.remove(it)
            }
            indexes.clear()
            adapter.notifyDataSetChanged()
            adapter.update()
        }

        val add : Button = findViewById(R.id.ajouter)
        add.setOnClickListener {
            val addStudent = Intent(this,AjouterEtudiant::class.java)
            launcher.launch(addStudent)
        }
    }

    val launcher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK){
                val nom = it?.data?.getStringExtra("nom")!!
                var prenom = it?.data?.getStringExtra("prenom")!!

                studentList.add(Etudiant(prenom,nom))
                adapter.notifyDataSetChanged()
                adapter.update()
            }
        }

    fun buildStudents() {
        var i : Int = 0
        val names : MutableList<String> = resources.getStringArray(R.array.prenom).toMutableList()
        val surnames : MutableList<String> = resources.getStringArray(R.array.nom).toMutableList()
        while(i < names.size) {
            studentList.add(Etudiant(names[i],surnames[i]))
            i++
        }
    }
}