package fr.irif.zielonka.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import fr.irif.zielonka.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setSupportActionBar(binding.myToolbar)

    }
/*
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_layout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.quitter -> {
                finish()
                true
            }
            R.id.ajout_auteur -> {
                val intent = Intent(this, AddAuthorsActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.ajout_livre -> {

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

*/

    fun startAuthorsActivity(view: View) {
        val intent = Intent(this, AddAuthorsActivity::class.java)
        startActivity(intent)
    }

    fun startBookActivity(v: View) {
        Toast.makeText(this, "à faire", Toast.LENGTH_LONG).show()
    }

    fun startAddAuthorsActivity(v: View) {
        val intent = Intent(this, AddAuthorsActivity::class.java)
        startActivity(intent)
    }
    fun startSearchAuthorsActivity(v: View) {
        val intent = Intent(this, SearchAuthorActivity::class.java)
        startActivity(intent)
    }
    fun initialiser(view: View) {
        val myDao = BooksDatabase.getDatabase(this).myDao()

        Log.d("MainActivity", "database obtained")
        val a = resources.getStringArray(R.array.authors)
        val authors: Array<AuthorName> =
            Array(a.size / 2) { i ->
                Log.d("creation", "i=$i")
                AuthorName(name = a[2 * i], firstName = a[2 * i + 1])
            }
        // below, in *authors, * is the spread operator, it transforms an array into something that
        // can be used as a parameter of a function that expects a vararg argument
        Thread {
            var id = myDao.insertAuthors(*authors)
            Log.d("MainActivity", "${id.toString()}")
            id = myDao.insertAuthors(authors[0], AuthorName("Henryk", "Batory"), authors[1])
            Log.d("MainActivity", "${id.toString()}")
            val v: Long = id.fold(0L) { acc: Long, n: Long -> if (n >= 0) acc + 1 else acc }
            Log.d("MainActivity", "liczba insecji $v")

        }
            .start()

    }
}
