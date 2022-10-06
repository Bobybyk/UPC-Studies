package fr.irif.zielonka.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.irif.zielonka.myapplication.databinding.ActivityAddAuthorsBinding

class AddAuthorsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddAuthorsBinding
    val model by lazy {
        ViewModelProvider(this).get(AddAuthorViewModel::class.java).apply {
            sortType = AddAuthorViewModel.byName
        }
    }
    private val adapter by lazy { AddAuthorAdapter(model.selectedAuthors, AddAuthorViewModel.byName)
        .apply { this.sortType = model.sortType }
    }
    private val TAG = "AddAuthors"

    // lateinit var sortType : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAuthorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

     //   setSupportActionBar( binding.myToolbar )

        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        /* quand model.authors change mettre à jour la liste d'auteurs dans adapter */
        model.loadAllAuthors().observe(this) {
            Log.d(TAG, "nouvelle liste auteurs")
            adapter.setAuthors(it, model.selectedAuthors)
        }

        model.insertInfo.observe(this){
            Toast.makeText(this, "inserer $it élément(s)", Toast.LENGTH_SHORT)
                .apply{ setGravity(Gravity.TOP,0,10) }.show()
        }
    }
/*
    private var searchItem : MenuItem? = null
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.author_menu_layout, menu)
        searchItem  = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return true
            }
            override fun onQueryTextChange(s: String): Boolean {
                Log.d("SearchView", "prefix=$s")
                model.loadAuthorsByNamePrefix( s.trim() ).observe( this@AddAuthorsActivity ){
                    Log.d("SearchView", "observed list = $it")
                    adapter.setAuthors(it, model.selectedAuthors )
                }
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean= when (item.itemId) {
        R.id.quitter -> {
            finish()
            true
        }
        R.id.byName -> {
            Log.d("AddAuthors", "menu byName")
            model.sortType = AddAuthorViewModel.byName
            adapter.sortType = AddAuthorViewModel.byName
            true
        }
        R.id.byFirstname -> {
            Log.d("AddAuthors", "menu byFirstname")
            model.sortType = AddAuthorViewModel.byFirstname
            adapter.sortType = AddAuthorViewModel.byFirstname
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
*/
    //callback function pour le bouton "ajouter auteur"
    fun addAuthor( view: View) {
        val n = binding.nomEdit.text.toString().trim()
        val p = binding.prenomEdit.text.toString().trim()
        if (n == "" && p == "") {
            afficherDialog("nom et prénom ne peuvent pas être vides tous les deux")
            return
        }
       // model.insertAuthor(nom = n, prenom = p)
    model.insertAuthors( AuthorName(n,p) )
        binding.nomEdit.text.clear()
        binding.prenomEdit.text.clear()
    }


    //calback function pour le bouton "supprimer auteurs"
    fun removeAuthors(v: View) {
        if (model.selectedAuthors.isEmpty()) {
            afficherDialog("aucun auteur sélectionné")
            return
        }
        AlertDialog.Builder(this)
            .setMessage("supprimer ${model.selectedAuthors.size} auteurs?")
            .setCancelable(false)
            .setPositiveButton("OK") { d, _ ->
                model.removeAuthors()
                d.dismiss()
            }
            .setNegativeButton("NON") { d, _ -> d.dismiss() }
            .show()
    }

    private fun afficherDialog(s: String) {
        AlertDialog.Builder(this)
            .setMessage(s)
            .setPositiveButton("OK") { d, _ -> d.dismiss() }
            .setCancelable(false)
            .show()
        return
    }
}

