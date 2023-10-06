package com.example.tp4

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.PrintWriter
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MyRecycleAdapter
    private val listEtudiant: MutableList<Etudiant> = mutableListOf()
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListEtu()

        recyclerView.hasFixedSize() /* pour améliorer les pérformances*/
        recyclerView.layoutManager = LinearLayoutManager(this) /* spécifier le layout de RecyclerView */

        /* associer l'adapter à RecyclerView*/
        adapter = MyRecycleAdapter(listEtudiant,
            ContextCompat.getColor(this@MainActivity, R.color.couleurPaire),
            ContextCompat.getColor(this@MainActivity, R.color.couleurImpaire),
            ContextCompat.getColor(this@MainActivity, R.color.couleurChecked),
            findViewById<Spinner>(R.id.spinner).selectedItem.toString()
        )
        recyclerView.adapter = adapter

        updateSort()
        deletUsers()
        addUser()
        saveUsers()
        readUsers()

    }

    private fun readUsers() {
        findViewById<Button>(R.id.lecture).setOnClickListener {
            val file = File(filesDir, "save_user" )
            Scanner(file).use {
                listEtudiant.clear()
                while(it.hasNextLine()){
                    val studnt= it.nextLine().split(";").toTypedArray()
                    listEtudiant.add(Etudiant(studnt[1], studnt[0]))
                    Log.d("MainActivity", Etudiant(studnt[1], studnt[0]).toString())
                }
                adapter.notifyDataSetChanged()
                adapter.update()
            }
        }
    }

    private fun saveUsers() {
        findViewById<Button>(R.id.sauvegarder).setOnClickListener {
            val file = File(filesDir, "save_user" )
            PrintWriter( file ).use{
                listEtudiant.forEach { etudiant ->
                    it.println(etudiant.nom + ";" + etudiant.prenom + ";" + adapter.getSelected().contains(etudiant))
                }
            }
        }
    }

    private fun updateSort() {
        findViewById<Spinner>(R.id.spinner).onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?,
                                        p1: View?, position: Int, p3: Long) {

                adapter.sortColumn=p0!!.getItemAtPosition(position).toString()
                adapter.notifyDataSetChanged()
                adapter.update()
                //TODO: recyclerView.swapAdapter( , false )

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("on peut laisser non-implemente")
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    val launcher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK){
                val nom = it?.data?.getStringExtra("Etudiant_nom")!!
                val prenom = it?.data?.getStringExtra("Etudiant_prenom")!!
                listEtudiant.add(Etudiant(prenom,nom))
                adapter.notifyDataSetChanged()
                adapter.update()
            }
        }

    private fun initListEtu() {
        val listNom = resources.getStringArray(R.array.nom).toMutableList()
        val listPrenom = resources.getStringArray(R.array.prenom).toMutableList()
        listPrenom.zip(listNom).forEach { pair ->
            listEtudiant.add(Etudiant(pair.component1(), pair.component2()))
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deletUsers() {
        findViewById<Button>(R.id.supprimer).setOnClickListener {
            println("-----------------------------------------------------------------------------")
            println(listEtudiant)
            println("-----------------------------------------------------------------------------")
            val listE = adapter.getSelected()
            listE.forEach{
                listEtudiant.remove(it)
            }
            listE.clear()
            adapter.notifyDataSetChanged()
            adapter.update()
        }
    }

    private fun addUser() {
        findViewById<Button>(R.id.ajouter).setOnClickListener {
            launcher.launch(Intent(this@MainActivity, AjouterEtudiant::class.java))
        }
    }

}