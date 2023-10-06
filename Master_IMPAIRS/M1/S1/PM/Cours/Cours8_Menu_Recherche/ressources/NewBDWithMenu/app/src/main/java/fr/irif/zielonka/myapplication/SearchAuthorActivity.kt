package fr.irif.zielonka.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Log.d
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.irif.zielonka.myapplication.databinding.ActivitySearchAuthorBinding

class SearchAuthorActivity : AppCompatActivity() {

    /* recuperer binding.  Le nom de lma class ActivitySearchAuthorBinding viend de nom de fichier layout
    * utilisé par cette activité : activity_search_author.xml  */
    private val binding by lazy { ActivitySearchAuthorBinding.inflate(layoutInflater) }

    /* recuperer une reference vers ViewModel */
    val model by lazy { ViewModelProvider(this).get(SearchAuthorViewModel::class.java) }

    /* adapter de RecyclerView */
    private val adapter by lazy { SearchAdapter(model.checkedAuthors) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* binding.root c'est la view à la racine de layout */
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(this)


        model.allAuthors.observe(this) {
            adapter.authors = it
            adapter.notifyDataSetChanged()
        }

        model.authors.observe(this) {
            adapter.authors = it
            adapter.notifyDataSetChanged()
        }

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                model.loadSomeAuthors(s.toString())
            }
        })
    }

    private var searchItem: MenuItem? = null

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.author_menu_layout, menu)


        /* le code suivant uniquement si vous avez la recherche dans le menu */
        searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                Log.d("Menu text submit", s)
                return true
            }

            override fun onQueryTextChange(s: String): Boolean {
                Log.d("Menu text change",s)
                return true
            }
        })

        /* toujours retourner true */
        return true

    }
    override fun onOptionsItemSelected(item: MenuItem) : Boolean = when ( item.itemId ) {
        R.id.quitter -> {
            finish()
            true
        }
        R.id.byName ->{
            Log.d("AddAuthors", "menu byName")
            true
        }
        R.id.byFirstname ->{
            Log.d("AddAuthors", "menu byFirstname")
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

}